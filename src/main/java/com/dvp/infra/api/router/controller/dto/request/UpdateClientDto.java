package com.dvp.infra.api.router.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.dvp.infra.api.router.controller.dto.response.PersonDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateClientDto {
    @NotBlank(message = "password is requiered")
    private String password;
    @JsonProperty("personal_information")
    @Valid
    private PersonDto personalInformation;
    @JsonIgnore
    private Long clientId;
    @JsonProperty("is_active")
    private Boolean isActive;
}
