package com.dvp.domain.port.db;

import com.dvp.domain.entities.Client;
import com.dvp.infra.adapter.db.entites.ClientsDto;

public interface ClientsPortRepository {
    Client save(ClientsDto client);
    Client getClientById(Long clientId);
    Client getClientByIdentificationTypeIdAndIdentificationNumber(Long typeId, Long number);
    void deleteClient(Long clientId);
    Client updateClient(ClientsDto client );
}
