package com.dvp.infra.adapter.db;

import com.dvp.UsersApplication;
import com.dvp.app.UsersService;
import com.dvp.domain.entities.User;
import com.dvp.domain.port.db.UsersPortRepository;
import com.dvp.infra.adapter.db.entites.UsersDto;
import com.dvp.infra.adapter.db.jpa.UsersJpaRepository;
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
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersApplication.class)
public class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @MockBean
    private UsersJpaRepository usersJpaRepository;

    @Test
    public void saveTestWhenSuccess() throws UserException {
        Mockito.when(usersJpaRepository.save(any())).thenReturn(getUsersDto());

        User response = usersRepository.save(getUsersDto());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getFirstName(), getUsersDto().getFirstName());
    }

    @Test
    public void getUserByIdTestWhenSuccess() throws UserException {
        Mockito.when(usersJpaRepository.findById(any())).thenReturn(Optional.ofNullable(getUsersDto()));

        User response = usersRepository.getUserById(1L);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getFirstName(), getUsersDto().getFirstName());
    }

    @Test
    public void getUsersTestWhenSuccess() throws UserException {
        Iterable<UsersDto> iterable = Collections.singletonList(getUsersDto());

        Mockito.when(usersJpaRepository.findAll()).thenReturn(iterable);

        List<User> response = usersRepository.getUsers();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.get(0).getFirstName(), getUsersDto().getFirstName());
    }

    @Test
    public void updateUserTestWhenSuccess() throws UserException {
        Mockito.when(usersJpaRepository.save(any())).thenReturn(getUsersDto());

        User response = usersRepository.updateUser(getUsersDto());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getFirstName(), getUsersDto().getFirstName());
    }

    @Test
    public void getUserByNameTestWhenSuccess() throws UserException {
        Mockito.when(usersJpaRepository.findByFirstNameAndLastName(any(), any())).thenReturn(getUsersDto());

        User response = usersRepository.getUserByName(getUsersDto().getFirstName(), getUsersDto().getLastName());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getFirstName(), getUsersDto().getFirstName());
    }

    private UsersDto getUsersDto() {
        return UsersDto.builder()
                .lastName("lastName")
                .firstName("firstName")
                .updateAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .createAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .userId(1L).build();
    }
}
