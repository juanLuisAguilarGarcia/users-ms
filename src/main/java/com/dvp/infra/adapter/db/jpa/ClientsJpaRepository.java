package com.dvp.infra.adapter.db.jpa;

import com.dvp.infra.adapter.db.entites.ClientsDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClientsJpaRepository extends CrudRepository<ClientsDto, Long> {

     ClientsDto findByClientIdAndIsActive(Long clientId, Boolean isActive);

     @Query(value = "SELECT c FROM ClientsDto c WHERE c.persons.identification.identificationTypeId = ?1 "
             + "AND c.persons.identificationNumber = ?2")
     ClientsDto findByIdentificationTypeIdAndNumber(Long typeId, Long number);
}
