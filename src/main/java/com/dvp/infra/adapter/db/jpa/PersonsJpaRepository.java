package com.dvp.infra.adapter.db.jpa;

import com.dvp.infra.adapter.db.entites.PersonsDto;
import org.springframework.data.repository.CrudRepository;

public interface PersonsJpaRepository extends CrudRepository<PersonsDto, Long> {
}
