package com.dvp.infra.api.router.controller.dto.response.client;

import com.dvp.infra.api.router.controller.dto.response.PersonDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDataDto {
    @JsonProperty("client_id")
    private Long clientId;
    @JsonProperty("is_active")
    private Boolean isActive;
    @NotBlank(message = "password is requiered")
    private String password;
    @JsonProperty("personal_information")
    @Valid
    private PersonDto personalInformation;
    @JsonProperty("create_at")
    private Timestamp createAt;
    @JsonProperty("update_at")
    private Timestamp updateAt;
}
