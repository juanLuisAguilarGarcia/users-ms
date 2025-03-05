package com.dvp.infra.api.router.facade;

import com.dvp.infra.api.router.controller.dto.response.client.ClientDto;
import com.dvp.infra.api.router.controller.error.exception.ClientException;
import com.dvp.domain.entities.Client;

public interface ClientsFacade {

    public ClientDto createClient(Client client) throws ClientException;

    public ClientDto getClientById(Long clientId) throws ClientException;

    public void deleteClient(Long clientId) throws ClientException;

    public ClientDto updateClient(Client client ) throws ClientException;
}
