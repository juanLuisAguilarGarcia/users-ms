package com.dvp.domain.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Builder
public class Client {
    private Long clientId;
    private Boolean isActive;
    private String password;
    private Person personalInformation;
    private Timestamp createAt;
    private Timestamp updateAt;
}
