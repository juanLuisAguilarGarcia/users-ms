package com.dvp.infra.adapter.db.jpa;

import com.dvp.infra.adapter.db.entites.IdentificationTypeDto;
import org.springframework.data.repository.CrudRepository;

public interface IdentificationTypeJpaRepository extends CrudRepository<IdentificationTypeDto, Long> {
     IdentificationTypeDto findByIdentificationTypeId(Long identificationTypeId);
}
