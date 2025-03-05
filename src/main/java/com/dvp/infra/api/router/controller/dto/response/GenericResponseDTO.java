package com.dvp.infra.api.router.controller.dto.response;

import com.dvp.infra.api.router.controller.error.ErrorConsts;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponseDTO {
    @Schema(example = ErrorConsts.MOCK_CODE_200)
    private String code;
    @Schema(example = ErrorConsts.MOCK_MENSAJE_OK)
    private String message;
}
