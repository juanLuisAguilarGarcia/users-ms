package com.dvp.infra.api.router.controller;

import com.dvp.UsersApplication;
import com.dvp.app.UsersService;
import com.dvp.infra.adapter.db.UsersRepository;
import com.dvp.infra.api.router.controller.dto.response.user.UserDataDto;
import com.dvp.infra.api.router.controller.dto.response.user.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UsersApplication.class })
@WebAppConfiguration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class UsersControllerIntegrationsTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UsersService usersService;

    @MockBean
    private UsersRepository usersRepository;

    private MockMvc mockMvc;

    private String body;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        this.body = "{ \n" +
                "    \"first_name\": \"Jose\",\n" +
                "    \"last_name\" : \"Lema\" \t\n" +
                "}";
    }

    @Test
    public void getUserByIdThenVerifyResponse() throws Exception {

        Mockito.when(usersService.getUserById(any())).thenReturn(getUserDto());


         this.mockMvc.perform(get("/user/{id}", "1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andReturn();

    }

    @Test
    public void createUserThenVerifyResponse() throws Exception {

        Mockito.when(usersService.createUser(any())).thenReturn(getUserDto());


        this.mockMvc.perform(post("/user" ).content(body).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(201)).andExpect(content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$.code").value("200"))
                .andReturn();

    }

    @Test
    public void getUsersThenVerifyResponse() throws Exception {

        Mockito.when(usersService.getUsers()).thenReturn(getUserDto());


        this.mockMvc.perform(get("/user" ))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andReturn();

    }

    @Test
    public void updateUserThenVerifyResponse() throws Exception {

        Mockito.when(usersService.updateUser(any())).thenReturn(getUserDto());


        this.mockMvc.perform(put("/user/{id}", "1" ).content(body).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andReturn();

    }

    private UserDto getUserDto() {
        UserDto result = UserDto.builder()
                .data(Collections.singletonList(UserDataDto.builder()
                        .lastName("lastName")
                        .firstName("firstName")
                        .updateAt(Timestamp.valueOf(LocalDateTime.now()))
                        .createAt(Timestamp.valueOf(LocalDateTime.now()))
                        .userId(1L).build()))
                .build();
        result.setCode("200");
        result.setMessage("user created");

        return result;
    }
}
