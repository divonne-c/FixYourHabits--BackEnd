package com.fixyourhabitsbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fixyourhabitsbackend.dtos.UserDto;
import com.fixyourhabitsbackend.security.CustomUserDetailsService;
import com.fixyourhabitsbackend.security.JwtUtil;
import com.fixyourhabitsbackend.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomUserDetailsService customUserDetailsService;

    @MockBean
    UserService userService;

    @MockBean
    JwtUtil jwtUtil;


    ///// Test User ////
    public UserDto userDto() {
        UserDto user = new UserDto();
        user.setUsername("user");
        user.setName("user");
        user.setPassword("password");
        user.setEmail("user@hotmail.com");
        user.setEnabled(true);

        return user;
    }

    public static String asJsonString(Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnAllUsers() throws Exception {

        List<UserDto> users = Arrays.asList(userDto());

        Mockito.when(userService.getAllUsers()).thenReturn(users);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/users"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username", is("user")));

    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldReturnUserByUsername() throws Exception {

        Mockito.when(userService.getUserByUsername("user")).thenReturn(userDto());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/users/user"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", is("user")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("user")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", is("password")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", is("user@hotmail.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.enabled", is(true)));
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldCreateUserWithUserProfile() throws Exception {

        Mockito.when(userService.createUser(userDto())).thenReturn("user");

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/users")
                        .content(asJsonString(userDto()))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateUserWithAdminProfile() throws Exception {

        Mockito.when(userService.createAdmin(userDto())).thenReturn("user");

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/users/admin")
                        .content(asJsonString(userDto()))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldDeleteUserWithUsername() throws Exception {

        Mockito.doNothing().when(userService).deleteUser(userDto().getUsername());

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/users/user"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNoContent());
    }

}