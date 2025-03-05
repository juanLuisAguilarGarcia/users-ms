package com.dvp.infra.api.router.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactInformationDto {
    @JsonProperty("telephone_number")
    private Long telephoneNumber;
}
