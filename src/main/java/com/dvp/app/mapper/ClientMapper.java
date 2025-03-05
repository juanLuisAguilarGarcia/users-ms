package com.dvp.app.mapper;

import com.dvp.infra.adapter.db.entites.ClientsDto;
import com.dvp.infra.adapter.db.entites.IdentificationTypeDto;
import com.dvp.infra.adapter.db.entites.PersonsDto;
import com.dvp.infra.api.router.controller.dto.response.IdentificationDto;
import com.dvp.infra.api.router.controller.dto.response.PersonDto;
import com.dvp.infra.api.router.controller.dto.response.client.ClientDto;
import com.dvp.domain.entities.Client;
import com.dvp.infra.api.router.controller.dto.response.ContactInformationDto;
import com.dvp.infra.api.router.controller.dto.response.client.ClientDataDto;
import org.mapstruct.Mapper;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    static ClientDataDto toDto(Client client){
        ClientDataDto clientDataDto = ClientDataDto.builder().clientId(client.getClientId()).build();

        clientDataDto.setPassword(client.getPassword());
        clientDataDto.setIsActive(client.getIsActive());
        clientDataDto.setPassword(client.getPassword());
        clientDataDto.setCreateAt(client.getCreateAt());
        clientDataDto.setUpdateAt(client.getUpdateAt());

        if(!Objects.isNull(client.getPersonalInformation())){
            clientDataDto.setPersonalInformation(PersonDto.builder()
                    .address(client.getPersonalInformation().getAddress())
                    .age(client.getPersonalInformation().getAge())
                    .firstName(client.getPersonalInformation().getFirstName())
                    .lastName(client.getPersonalInformation().getLastName())
                    .gender(client.getPersonalInformation().getGender()).build());

            if(!Objects.isNull(client.getPersonalInformation().getContactInformation())){
                clientDataDto.getPersonalInformation().setContactInformation(ContactInformationDto.builder()
                        .telephoneNumber(client.getPersonalInformation().getContactInformation().getTelephoneNumber()).build());
            }

            if(!Objects.isNull(client.getPersonalInformation().getIdentification())){
                clientDataDto.getPersonalInformation().setIdentification(IdentificationDto.builder()
                        .number(client.getPersonalInformation().getIdentification().getNumber())
                        .typeId(client.getPersonalInformation().getIdentification().getTypeId()).build());
            }
        }

        return clientDataDto;
    }

    static ClientDto toClientDto(ClientDataDto client, String msg){
        ClientDto clientDto =  ClientDto.builder().data(client).build();
        clientDto.setCode(String.valueOf(HttpStatus.OK.value()));
        clientDto.setMessage(msg);

        return clientDto;
    }

    static ClientsDto toClientEntityDto(Client client) {
        PersonsDto personsDto = new PersonsDto();

        if (Objects.isNull(client) ){
            return ClientsDto.builder().build();
        }

        if(!Objects.isNull(client.getPersonalInformation())) {
            personsDto = PersonsDto.builder()
                    .personId(client.getPersonalInformation().getPersonId())
                    .address(client.getPersonalInformation().getAddress())
                    .age(client.getPersonalInformation().getAge())
                    .firstName(client.getPersonalInformation().getFirstName())
                    .lastName(client.getPersonalInformation().getLastName())
                    .gender(client.getPersonalInformation().getGender())
                    .telephonNumber(client.getPersonalInformation().getContactInformation().getTelephoneNumber())
                    .updateAt(Timestamp.valueOf(LocalDateTime.now()))
                    .identification(IdentificationTypeDto.builder().build()).build();

            if(!Objects.isNull(client.getPersonalInformation().getContactInformation())) {
                personsDto.setIdentificationNumber(client.getPersonalInformation().getIdentification().getNumber());
            }

            if(!Objects.isNull(client.getPersonalInformation().getIdentification())) {
                personsDto.setIdentification(IdentificationTypeDto.builder().identificationTypeId(
                        client.getPersonalInformation().getIdentification().getTypeId()
                ).build());
            }
        }

        return  ClientsDto.builder()
                .clientId(client.getClientId())
                .isActive(true)
                .createAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .updateAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .persons(personsDto)
                .password(client.getPassword()).build();
    }
}
