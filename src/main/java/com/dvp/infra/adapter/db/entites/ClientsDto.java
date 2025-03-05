package com.dvp.infra.adapter.db.entites;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
public class ClientsDto {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "client_id")
    private Long clientId;

    @Column(length = 100)
    private String password;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_person_id", referencedColumnName = "person_id")
    private PersonsDto persons;

    @Column(name = "create_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable = false)
    private Timestamp createAt;

    @Column(name = "update_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updateAt;
}
