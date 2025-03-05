package com.dvp.infra.api.router.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDto {
    @JsonProperty("first_name")
    @NotBlank
    private String firstName;
    @JsonProperty("last_name")
    @NotBlank
    private String lastName;
    @Size(max = 1 )
    private String gender;
    private Integer age;
    @NotNull
    @Valid
    private IdentificationDto identification;
    private String address;
    @JsonProperty("contact_information")
    private ContactInformationDto contactInformation;
}
