package com.dvp.infra.api.router.controller.error.handler;

import com.dvp.infra.api.router.controller.UsersController;
import com.dvp.infra.api.router.controller.dto.GenericResponseDTO;
import com.dvp.infra.api.router.controller.dto.request.CreateAndUpdateUserDto;
import com.dvp.infra.api.router.controller.error.exception.UserException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.context.request.WebRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ErrorHandlerTest {
    private ErrorHandler errorHandler;
    private UsersController usersController;
    private DataBinder dataBinder;
    private WebRequest request;

    @Before
    public void init(){
        errorHandler = new ErrorHandler();
        usersController = new UsersController();
        dataBinder = new DataBinder(usersController);
        request = Mockito.mock(WebRequest.class);
    }

    @Test
    public void handleMethodArgumentNotValidTest() throws NoSuchMethodException{
        MethodArgumentNotValidException methodArgumentNotValidException = new MethodArgumentNotValidException(
                new MethodParameter(UsersController.class.getMethod("createUser", CreateAndUpdateUserDto.class),-1),
                dataBinder.getBindingResult());
        assertNotNull(errorHandler.handleMethodArgumentNotValid(methodArgumentNotValidException ));
    }

    @Test
    public void handleServletRequestBindingExceptionTest() throws NoSuchMethodException {
        MissingPathVariableException missingPathVariableException = new MissingPathVariableException("satellite_name",
                new MethodParameter(UsersController.class.getMethod("createUser", CreateAndUpdateUserDto.class),-1));
        assertNotNull(errorHandler.handleServletRequestBindingException(missingPathVariableException ));
    }

    @Test
    public void genericExceptionTest() throws NoSuchMethodException {
        ResponseEntity<GenericResponseDTO> response = errorHandler.genericException(new UserException("422-1", "error"));

        assertNotNull(response);
        assertEquals("422-1", response.getBody().getCode());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    public void exceptionTest() throws NoSuchMethodException {
        ResponseEntity<GenericResponseDTO> response = errorHandler.exception(new Exception( "error"));

        assertNotNull(response);
        assertEquals("error", response.getBody().getMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}

