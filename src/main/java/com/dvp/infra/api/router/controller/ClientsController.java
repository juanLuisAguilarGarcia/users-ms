package com.dvp.infra.api.router.controller;

import com.dvp.infra.api.router.controller.dto.GenericResponseDTO;
import com.dvp.infra.api.router.controller.dto.request.CreateClientDto;
import com.dvp.infra.api.router.controller.dto.request.UpdateClientDto;
import com.dvp.infra.api.router.controller.dto.response.client.ClientDto;
import com.dvp.infra.api.router.controller.error.exception.ClientException;
import com.dvp.infra.api.router.controller.mapper.ClientDtoMapper;
import com.dvp.infra.api.router.RouterConsts;
import com.dvp.infra.api.router.facade.ClientsFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import static com.dvp.infra.api.router.RouterConsts.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@CrossOrigin(RouterConsts.CROSS_ORIGIN)
@RestController
@RequestMapping(path = RouterConsts.CONTROLLER_PATH)
@Tag(name = RouterConsts.API)
public class ClientsController {

    @Autowired
    private ClientDtoMapper clientDtoMapper;

    @Autowired
    private ClientsFacade clientsFacade;

    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = RouterConsts.API_OPERATION_CREATE_CLIENT, description = RouterConsts.NOTE_API_OPERATION_CREATE_CLIENT)
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = RouterConsts.API_RESPONSE_COD_200,
                    content =  { @Content( schema = @Schema(implementation =  ClientDto.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = RouterConsts.API_RESPONSE_COD_400,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = RouterConsts.API_RESPONSE_COD_404,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "422", description = RouterConsts.API_RESPONSE_COD_422,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = RouterConsts.API_RESPONSE_COD_500,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<ClientDto> createClient(
            @Parameter(description = RouterConsts.API_PARAM_REQUEST_CREATE_CLIENT, required = true) @Validated @RequestBody(required = true) CreateClientDto clientDto) throws ClientException {
        log.info(String.format(MSG_PROCESS, "init", "create",  clientDto.getPersonalInformation().getIdentification().getNumber()));

        ClientDto response = clientsFacade.createClient(ClientDtoMapper.toEntity(clientDto));

        log.info(String.format(MSG_PROCESS, "end", "create",  clientDto.getPersonalInformation().getIdentification().getNumber()));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{client_id}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = RouterConsts.API_OPERATION_GET_CLIENT_BY_ID, description = RouterConsts.NOTE_API_OPERATION_GET_BY_ID_CLIENT)
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = RouterConsts.API_RESPONSE_COD_200,
            content =  { @Content( schema = @Schema(implementation =  ClientDto.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = RouterConsts.API_RESPONSE_COD_400,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = RouterConsts.API_RESPONSE_COD_404,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "422", description = RouterConsts.API_RESPONSE_COD_422,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = RouterConsts.API_RESPONSE_COD_500,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<ClientDto> getClientById(
            @Parameter(description = RouterConsts.API_PARAM_REQUEST_GET_CLIENT, required = true) @PathVariable(name = PARAM_CLIENT_ID ) Long clientId) throws ClientException {
        log.info(String.format(MSG_PROCESS, "init", "get",  clientId));

        ClientDto response = clientsFacade.getClientById(clientId);

        log.info(String.format(MSG_PROCESS, "end", "get",  clientId));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{client_id}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = RouterConsts.API_OPERATION_DELETE_CLIENT, description = RouterConsts.NOTE_API_OPERATION_DELETE_CLIENT)
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = RouterConsts.API_RESPONSE_COD_200,
            content =  { @Content( schema = @Schema(implementation =  GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = RouterConsts.API_RESPONSE_COD_400,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = RouterConsts.API_RESPONSE_COD_404,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "422", description = RouterConsts.API_RESPONSE_COD_422,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = RouterConsts.API_RESPONSE_COD_500,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<GenericResponseDTO> deleteClient(
            @Parameter(description = RouterConsts.API_PARAM_REQUEST_GET_CLIENT, required = true) @PathVariable(name = PARAM_CLIENT_ID) Long clientId) throws ClientException {
        log.info(String.format(MSG_PROCESS, "init", "delete",  clientId));

        clientsFacade.deleteClient(clientId);

        log.info(String.format(MSG_PROCESS, "end", "delete",  clientId));
        return ResponseEntity.ok(GenericResponseDTO.builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message(MSG_CONFIRMATION_DELETE).build());
    }

    @PutMapping(value = "/{client_id}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = RouterConsts.API_OPERATION_CREATE_CLIENT, description = RouterConsts.NOTE_API_OPERATION_UPDATE_CLIENT)
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = RouterConsts.API_RESPONSE_COD_200,
            content =  { @Content( schema = @Schema(implementation =  ClientDto.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = RouterConsts.API_RESPONSE_COD_400,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = RouterConsts.API_RESPONSE_COD_404,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "422", description = RouterConsts.API_RESPONSE_COD_422,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = RouterConsts.API_RESPONSE_COD_500,
                    content =  { @Content( schema = @Schema(implementation = GenericResponseDTO.class), mediaType = APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<ClientDto> updateClient(
            @Parameter(description = RouterConsts.API_PARAM_REQUEST_GET_CLIENT, required = true) @PathVariable(name = PARAM_CLIENT_ID) Long clientId,
            @Parameter(description = RouterConsts.API_PARAM_REQUEST_UPDATE_CLIENT, required = true) @Validated @RequestBody(required = true) UpdateClientDto clientDto) throws ClientException {
        log.info(String.format(MSG_PROCESS, "init", "update",  clientId));

        clientDto.setClientId(clientId);
        ClientDto response = clientsFacade.updateClient(ClientDtoMapper.updateToEntity(clientDto));

        log.info(String.format(MSG_PROCESS, "init", "update",  clientId));
        return ResponseEntity.ok(response);
    }
}
