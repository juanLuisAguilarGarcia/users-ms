package com.dvp.app;

import com.dvp.app.mapper.ClientMapper;
import com.dvp.domain.entities.Client;
import com.dvp.domain.port.db.ClientsPortRepository;
import com.dvp.infra.adapter.db.entites.ClientsDto;
import com.dvp.infra.api.router.controller.dto.response.client.ClientDto;
import com.dvp.infra.api.router.controller.error.exception.ClientException;
import com.dvp.infra.api.router.facade.ClientsFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.dvp.app.ServiceConsts.*;

@Slf4j
@Service
public class ClientsService implements ClientsFacade {

    @Autowired
    private ClientsPortRepository clientsPortRepository;

    public ClientDto createClient(Client clientToSave) throws ClientException {
        try{
            Client client = clientsPortRepository.save(
                    ClientMapper.toClientEntityDto(
                            validClientBeforeCreate(clientToSave)));

            client.setCreateAt(client.getUpdateAt());
            log.info(String.format(MSG_PROCESS_SERVICE, "created", "identification_number: ", clientToSave.getPersonalInformation().getIdentification().getNumber()));
            return ClientMapper.toClientDto(ClientMapper.toDto(client), MSG_CLIENT_CREATED);
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "created",  "identification_number: ", clientToSave.getPersonalInformation().getIdentification().getNumber(),
                    ex.getMessage()));
            throw new ClientException("422-1", ex.getMessage());
        }
    }

    public ClientDto getClientById(Long clientId) throws ClientException {
        try{
            Client client = existsClient(clientId);
            log.info(String.format(MSG_PROCESS_SERVICE, "getById", "clientId: ", clientId));
            return ClientMapper.toClientDto(ClientMapper.toDto(client), MSG_CLIENT_GET);
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "getById",  "clientId: ", clientId,
                    ex.getMessage()));
            throw new ClientException("422-2", ex.getMessage());
        }

    }

    public void deleteClient(Long clientId) throws ClientException {
        try{
            Client client = existsClient(clientId);

            ClientsDto clientEntity = ClientMapper.toClientEntityDto(client);
            clientEntity.setIsActive(false);

            clientsPortRepository.save(clientEntity);
            log.info(String.format(MSG_PROCESS_SERVICE, "delete", "clientId: ", clientId));
            return;
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "delete",  "clientId: ", clientId,
                    ex.getMessage()));
            throw new ClientException("422-3", ex.getMessage());
        }
    }

    public ClientDto updateClient(Client clientToUpdate) throws ClientException {
        try{
            Client clientExists = existsClient(clientToUpdate.getClientId());

            clientToUpdate.getPersonalInformation().setPersonId(clientExists.getPersonalInformation().getPersonId());
            Client client = clientsPortRepository.updateClient(ClientMapper.toClientEntityDto(clientToUpdate));
            log.info(String.format(MSG_PROCESS_SERVICE, "update", "clientId: ", clientToUpdate.getClientId()));
            return ClientMapper.toClientDto(ClientMapper.toDto(client), MSG_CLIENT_UPDATE);
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "update",  "clientId: ", clientToUpdate.getClientId(),
                    ex.getMessage()));
            throw new ClientException("422-4", ex.getMessage());
        }
    }

    private Client existsClient(Long clientId) throws ClientException {
        Client client = clientsPortRepository.getClientById(clientId);

        if(Objects.isNull(client.getClientId())  || client.getClientId() < 1L) {
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "exists",  "clientId: ", clientId,
                    "client not found"));
            throw new ClientException("422-5", "client not found");
        }

        return client;
    }

    private Client validClientBeforeCreate(Client clientToCreate) throws ClientException {
        Client client = clientsPortRepository.getClientByIdentificationTypeIdAndIdentificationNumber(
                clientToCreate.getPersonalInformation().getIdentification().getTypeId(), clientToCreate.getPersonalInformation().getIdentification().getNumber());

        if(!Objects.isNull(client.getClientId()) && client.getClientId() >= 1L ) {
            if(client.getIsActive()) {
                log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "exists",  "identification_number: ", clientToCreate.getPersonalInformation().getIdentification().getNumber(),
                        "Client exists in system."));
                throw new ClientException("422-5", "Client exists in system.");
            } else {
                log.info(String.format(MSG_PROCESS_SERVICE, "exists so the information will be updated and actived", "clientId: ", client.getClientId()));
                clientToCreate.setClientId(client.getClientId());
                clientToCreate.getPersonalInformation().setPersonId(client.getPersonalInformation().getPersonId());
                clientToCreate.setIsActive(true);
                return clientToCreate;
            }
        }else {
            return clientToCreate;
        }

    }
}
