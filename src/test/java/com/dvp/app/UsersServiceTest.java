package com.dvp.app;

import com.dvp.UsersApplication;
import com.dvp.domain.entities.User;
import com.dvp.domain.port.db.UsersPortRepository;
import com.dvp.infra.api.router.controller.dto.response.user.UserDto;
import com.dvp.infra.api.router.controller.error.exception.UserException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersApplication.class)
public class UsersServiceTest {

    @Autowired
    private UsersService usersService;

    @MockBean
    private UsersPortRepository usersPortRepository;

    @Test
    public void createUserTestWhenSuccess() throws UserException {
        Mockito.when(usersPortRepository.save(any())).thenReturn(getUser());
        Mockito.when(usersPortRepository.getUserByName(any(), any()))
                .thenReturn(User.builder().build());

        UserDto response = usersService.createUser(getUser());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getData().get(0).getFirstName(), getUser().getFirstName());
    }

    @Test
    public void createClientTestWhenExists() throws UserException {
        Mockito.when(usersPortRepository.save(any())).thenReturn(getUser());
        Mockito.when(usersPortRepository.getUserByName(any(), any()))
                .thenReturn(getUser());

        Assertions.assertThrows(UserException.class, () -> usersService.createUser(getUser()));
    }

    @Test
    public void createClientTestWhenDataAccessException() throws UserException {
        Mockito.when(usersPortRepository.save(any())).thenThrow(new RecoverableDataAccessException("jpa error"));
        Mockito.when(usersPortRepository.getUserByName(any(), any()))
                .thenReturn(User.builder().build());

        Assertions.assertThrows(UserException.class, () -> usersService.createUser(getUser()));
    }

    @Test
    public void getUserByIdTestWhenSuccess() throws UserException {
        Mockito.when(usersPortRepository.getUserById(any())).thenReturn(getUser());

        UserDto response = usersService.getUserById(1L);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getData().get(0).getFirstName(), getUser().getFirstName());
    }

    @Test
    public void getUserByIdTestWhenExists() throws UserException {
        Mockito.when(usersPortRepository.getUserById(any())).thenReturn(User.builder().build());

        Assertions.assertThrows(UserException.class, () -> usersService.getUserById(1L));
    }

    @Test
    public void getUserByIdTestWhenDataAccessException() throws UserException {
        Mockito.when(usersPortRepository.getUserById(any())).thenThrow(new RecoverableDataAccessException("jpa error"));

        Assertions.assertThrows(UserException.class, () -> usersService.getUserById(1L));
    }

    @Test
    public void getUsersTestWhenSuccess() throws UserException {
        Mockito.when(usersPortRepository.getUsers()).thenReturn(Collections.singletonList(getUser()));

        UserDto response = usersService.getUsers();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getData().get(0).getFirstName(), getUser().getFirstName());
    }

    @Test
    public void getUsersTestWhenDataAccessException() throws UserException {
        Mockito.when(usersPortRepository.getUsers()).thenThrow(new RecoverableDataAccessException("jpa error"));

        Assertions.assertThrows(UserException.class, () -> usersService.getUsers());
    }

    @Test
    public void updateUserTestWhenSuccess() throws UserException {
        Mockito.when(usersPortRepository.updateUser(any())).thenReturn(getUser());
        Mockito.when(usersPortRepository.getUserById(any())).thenReturn(getUser());

        UserDto response = usersService.updateUser(getUser());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getData().get(0).getFirstName(), getUser().getFirstName());
    }

    @Test
    public void updateUserTestWhenExists() throws UserException {
        Mockito.when(usersPortRepository.getUserById(any())).thenReturn(new User());

        Assertions.assertThrows(UserException.class, () -> usersService.updateUser(getUser()));
    }

    @Test
    public void updateUserTestWhenDataAccessException() throws UserException {
        Mockito.when(usersPortRepository.updateUser(any())).thenThrow(new RecoverableDataAccessException("jpa error"));
        Mockito.when(usersPortRepository.getUserById(any())).thenReturn(getUser());

        Assertions.assertThrows(UserException.class, () -> usersService.updateUser(getUser()));
    }

    private User getUser() {
        return User.builder()
                .lastName("lastName")
                .firstName("firstName")
                .updateAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .createAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .userId(1L).build();
    }
}
