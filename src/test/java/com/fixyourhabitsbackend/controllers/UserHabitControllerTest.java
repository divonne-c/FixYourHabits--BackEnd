package com.fixyourhabitsbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fixyourhabitsbackend.dtos.UserHabitDto;
import com.fixyourhabitsbackend.models.UserHabit;
import com.fixyourhabitsbackend.security.CustomUserDetailsService;
import com.fixyourhabitsbackend.security.JwtUtil;
import com.fixyourhabitsbackend.services.UserHabitService;
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

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserHabitController.class)
class UserHabitControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomUserDetailsService customUserDetailsService;

    @MockBean
    UserHabitService userHabitService;

    @MockBean
    JwtUtil jwtUtil;

    public UserHabitDto userHabitDto() {
        UserHabitDto userHabitDto = new UserHabitDto();

        userHabitDto.setId(1L);
        userHabitDto.setName("Workout");
        userHabitDto.setType("Sport");

        return userHabitDto;
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
    @WithMockUser(roles = "USER")
    void getAllUserHabits() throws Exception {
        List<UserHabitDto> userHabitDtos = Arrays.asList(userHabitDto());

        Mockito.when(userHabitService.getAllUserHabits()).thenReturn(userHabitDtos);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/userhabits"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getUserHabitById() throws Exception {

        Mockito.when(userHabitService.getUserHabitById(1L)).thenReturn(userHabitDto());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/userhabits/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Workout")));
    }

    @Test
    @WithMockUser(roles = "USER")
    void createUserHabit() throws Exception {
        UserHabit userHabit = new UserHabit();
        userHabit.setId(userHabitDto().getId());

        Mockito.when(userHabitService.createUserHabit(userHabit)).thenReturn(userHabitDto());

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/userhabits")
                        .content(asJsonString(userHabit))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    @WithMockUser(roles = "USER")
    void deleteUserHabitWithUserAuthorization() throws Exception {

        Mockito.doNothing().when(userHabitService).deleteUserHabit(userHabitDto().getId());

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/userhabits/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNoContent());
    }

}