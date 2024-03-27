package com.desarrollo.tienda.controller;

import com.desarrollo.tienda.dto.UserDto;
import com.desarrollo.tienda.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void getUsers() throws Exception {
        // simular datos de usuario

        List<UserDto> users = new ArrayList<>();
        users.add(new UserDto());
        users.add(new UserDto());

        // Simular comportaiento del servicio de usuario
        Mockito.when(userService.getAllUsers()).thenReturn(users);

        // Realizar peticion GET
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/user"))
                .andExpect(status().isOk())
                .andReturn();

        // Verificar que la respuesta no sea nula
        String response = mvcResult.getResponse().getContentAsString();
        // Afirmar el contenido de la respuesta usando AssertJ
        assertThat(response, containsString("[{...},{...}"));
    }
}