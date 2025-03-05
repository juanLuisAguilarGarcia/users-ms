package com.dvp.infra.adapter.db.entites;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "identification_type")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationTypeDto {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "identification_type_id")
    private Long identificationTypeId;

    @Column(length = 100)
    private String description;

    @Column(name = "create_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable = false)
    private Timestamp createAt;

    @Column(name = "update_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updateAt;

    @OneToMany(mappedBy= "identification")
    private List<PersonsDto> people;
}
