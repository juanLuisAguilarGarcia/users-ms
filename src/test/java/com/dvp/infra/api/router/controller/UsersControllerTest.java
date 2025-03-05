package com.dvp.infra.api.router.controller;

import com.dvp.UsersApplication;
import com.dvp.app.UsersService;
import com.dvp.domain.entities.User;
import com.dvp.domain.port.db.UsersPortRepository;
import com.dvp.infra.api.router.controller.dto.GenericResponseDTO;
import com.dvp.infra.api.router.controller.dto.request.CreateAndUpdateUserDto;
import com.dvp.infra.api.router.controller.dto.response.user.UserDataDto;
import com.dvp.infra.api.router.controller.dto.response.user.UserDto;
import com.dvp.infra.api.router.controller.error.exception.UserException;
import com.dvp.infra.api.router.controller.mapper.UserDtoMapper;
import com.dvp.infra.api.router.facade.UsersFacade;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersApplication.class)
public class UsersControllerTest {

    @Autowired
    private UsersController usersController;

    @MockBean
    private UsersService usersService;

    @Test
    public void createUserTestWhenSuccess() throws UserException {
        Mockito.when(usersService.createUser(any())).thenReturn(getUserDto());

        ResponseEntity<UserDto> response = usersController.createUser(getCreateAndUpdateUserDto());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Objects.requireNonNull(response.getBody()).getData().get(0).getFirstName(), getUserDto().getData().get(0).getFirstName());
    }

    @Test
    public void createUserTestWhenUserException() throws UserException {
        Mockito.when(usersService.createUser(any())).thenThrow(new UserException("422-1", "error"));

        Assertions.assertThrows(UserException.class, () -> usersController.createUser(getCreateAndUpdateUserDto()));
    }

    @Test
    public void getUserByIdTestWhenSuccess() throws UserException {
        Mockito.when(usersService.getUserById(any())).thenReturn(getUserDto());

        ResponseEntity<UserDto> response = usersController.getUserById(1L);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Objects.requireNonNull(response.getBody()).getData().get(0).getFirstName(), getUserDto().getData().get(0).getFirstName());
    }

    @Test
    public void getUsersTestWhenSuccess() throws UserException {
        Mockito.when(usersService.getUsers()).thenReturn(getUserDto());

        ResponseEntity<UserDto> response = usersController.getUsers();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Objects.requireNonNull(response.getBody()).getData().get(0).getFirstName(), getUserDto().getData().get(0).getFirstName());
    }

    @Test
    public void updateUserTestWhenSuccess() throws UserException {
        Mockito.when(usersService.updateUser(any())).thenReturn(getUserDto());

        ResponseEntity<UserDto> response = usersController.updateUser(1L, getCreateAndUpdateUserDto());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Objects.requireNonNull(response.getBody()).getData().get(0).getFirstName(), getUserDto().getData().get(0).getFirstName());
    }

    private UserDto getUserDto() {
        return UserDto.builder()
                .data(Collections.singletonList(UserDataDto.builder()
                        .lastName("lastName")
                        .firstName("firstName")
                        .updateAt(Timestamp.valueOf(LocalDateTime.now()))
                        .createAt(Timestamp.valueOf(LocalDateTime.now()))
                        .userId(1L).build()))
                .build();
    }

    CreateAndUpdateUserDto getCreateAndUpdateUserDto(){
        return CreateAndUpdateUserDto.builder()
                .lastName("lastName")
                .firstName("firstName")
                .build();
    }
}
