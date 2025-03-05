package com.dvp.infra.api.router.controller.mapper;

import com.dvp.infra.api.router.controller.dto.request.CreateClientDto;
import com.dvp.infra.api.router.controller.dto.request.UpdateClientDto;
import com.dvp.domain.entities.Client;
import com.dvp.domain.entities.ContactInformation;
import com.dvp.domain.entities.Identification;
import com.dvp.domain.entities.Person;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface ClientDtoMapper {

    static Client toEntity(CreateClientDto createClientDto){
        if(Objects.isNull(createClientDto)){
            return Client.builder().build();
        }

        Client client = Client.builder()
                .password(createClientDto.getPassword()).build();

        if(!Objects.isNull(createClientDto.getPersonalInformation())){
            client.setPersonalInformation(Person.builder()
                    .gender(createClientDto.getPersonalInformation().getGender())
                    .lastName(createClientDto.getPersonalInformation().getLastName())
                    .firstName(createClientDto.getPersonalInformation().getFirstName())
                    .age(createClientDto.getPersonalInformation().getAge())
                    .address(createClientDto.getPersonalInformation().getAddress()).build());

            if(!Objects.isNull(createClientDto.getPersonalInformation().getIdentification())){
                client.getPersonalInformation().setIdentification(Identification.builder()
                        .typeId(createClientDto.getPersonalInformation().getIdentification().getTypeId())
                        .number(createClientDto.getPersonalInformation().getIdentification().getNumber()).build());
            }

            if(!Objects.isNull(createClientDto.getPersonalInformation().getContactInformation())){
                client.getPersonalInformation().setContactInformation(ContactInformation.builder()
                        .telephoneNumber(createClientDto.getPersonalInformation().getContactInformation().getTelephoneNumber()).build());
            }

        }

        return client;
    }

    static Client updateToEntity(UpdateClientDto updateClientDto){
        if(Objects.isNull(updateClientDto)){
            return Client.builder().build();
        }

        Client client = Client.builder()
                .clientId(updateClientDto.getClientId())
                .isActive(updateClientDto.getIsActive())
                .password(updateClientDto.getPassword()).build();

        if(!Objects.isNull(updateClientDto.getPersonalInformation())){
            client.setPersonalInformation(Person.builder()
                    .gender(updateClientDto.getPersonalInformation().getGender())
                    .lastName(updateClientDto.getPersonalInformation().getLastName())
                    .firstName(updateClientDto.getPersonalInformation().getFirstName())
                    .age(updateClientDto.getPersonalInformation().getAge())
                    .address(updateClientDto.getPersonalInformation().getAddress()).build());

            if(!Objects.isNull(updateClientDto.getPersonalInformation().getIdentification())){
                client.getPersonalInformation().setIdentification(Identification.builder()
                        .typeId(updateClientDto.getPersonalInformation().getIdentification().getTypeId())
                        .number(updateClientDto.getPersonalInformation().getIdentification().getNumber()).build());
            }

            if(!Objects.isNull(updateClientDto.getPersonalInformation().getContactInformation())){
                client.getPersonalInformation().setContactInformation(ContactInformation.builder()
                        .telephoneNumber(updateClientDto.getPersonalInformation().getContactInformation().getTelephoneNumber()).build());
            }

        }

        return client;
    }
}
