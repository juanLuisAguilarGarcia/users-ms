package com.dvp.infra.api.router.controller.dto.request;

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
public class CreateClientDto {
    @NotBlank(message = "password is requiered")
    private String password;
    @JsonProperty("personal_information")
    @Valid
    private PersonDto personalInformation;
}
