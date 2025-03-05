package com.dvp.infra.api.router;

public class RouterConsts {

    private RouterConsts(){}
    /**
     * componentes
     */
    public static final String COMPONENT_SCAN = "com.pichincha";

    /**
     * Controller config
     */
    public static final String API = "Clients";
    public static final String CROSS_ORIGIN = "*";
    public static final String CONTROLLER_PATH = "/client";

    /**
     * operaciones o metodos
     */
    public static final String API_OPERATION_CREATE_CLIENT = "Create a new client";
    public static final String API_OPERATION_GET_CLIENT_BY_ID = "Get client by id information";
    public static final String API_OPERATION_DELETE_CLIENT = "Delete client information";
    public static final String API_OPERATION_UPDATE_CLIENT = "Update client information";

    /**
     * descripcion de las operaciones o metodos
     */
    public static final String NOTE_API_OPERATION_CREATE_CLIENT = "In charge of create a new clients.";
    public static final String NOTE_API_OPERATION_GET_BY_ID_CLIENT = "In charge of get clients information filter by Id.";
    public static final String NOTE_API_OPERATION_DELETE_CLIENT = "In charge of delete a clients.";
    public static final String NOTE_API_OPERATION_UPDATE_CLIENT = "In charge of update clients infromation.";

    /**
     * mensajes de respuesta de acuerdo al codigo http
     */
    public static final String API_RESPONSE_COD_200 = "successful process";
    public static final String API_RESPONSE_COD_400 = "Some parameter is missing in the header";
    public static final String API_RESPONSE_COD_404 = "source not found.";
    public static final String API_RESPONSE_COD_422 = "Functional error in the application";
    public static final String API_RESPONSE_COD_500 = "Unknown error";

    /**
     * params
     */
    public static final String PARAM_CLIENT_ID = "client_id";

    /**
     * swagger param
     */
    public static final String API_PARAM_REQUEST_CREATE_CLIENT = "Body mapped to CreateClientDto.";
    public static final String API_PARAM_REQUEST_UPDATE_CLIENT = "Body update mapped to CreateClientDto.";
    public static final String API_PARAM_REQUEST_GET_CLIENT = "Id of client.";

    /**
     * messages
     */
    public static final String MSG_CONFIRMATION_DELETE = "Record deleted successful.";
    public static final String MSG_PROCESS = "%s %s client: %s.";
}
