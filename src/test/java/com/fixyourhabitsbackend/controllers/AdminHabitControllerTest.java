package com.fixyourhabitsbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fixyourhabitsbackend.dtos.AdminHabitDto;
import com.fixyourhabitsbackend.models.AdminHabit;
import com.fixyourhabitsbackend.services.CustomUserDetailsService;
import com.fixyourhabitsbackend.utils.JwtUtil;
import com.fixyourhabitsbackend.services.AdminHabitService;
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

@WebMvcTest(AdminHabitController.class)
class AdminHabitControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomUserDetailsService customUserDetailsService;

    @MockBean
    AdminHabitService adminHabitService;

    @MockBean
    JwtUtil jwtUtil;

    public AdminHabitDto adminHabitDto() {
        AdminHabitDto adminHabitDto = new AdminHabitDto();

        adminHabitDto.setId(1L);
        adminHabitDto.setName("Workout");
        adminHabitDto.setType("Sport");

        return adminHabitDto;
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
    void getAllAdminHabits() throws Exception {
        List<AdminHabitDto> adminHabitDtos = Arrays.asList(adminHabitDto());

        Mockito.when(adminHabitService.getAllAdminHabits()).thenReturn(adminHabitDtos);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/adminhabits"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAdminHabitById() throws Exception {

        Mockito.when(adminHabitService.getAdminHabitById(1L)).thenReturn(adminHabitDto());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/adminhabits/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Workout")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createAdminHabit() throws Exception {
        AdminHabit adminHabit = new AdminHabit();
        adminHabit.setId(adminHabitDto().getId());

        Mockito.when(adminHabitService.createAdminHabit(adminHabit)).thenReturn(adminHabitDto());

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/adminhabits")
                        .content(asJsonString(adminHabit))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteAdminHabitWithAdminAuthorization() throws Exception {

        Mockito.doNothing().when(adminHabitService).deleteAdminHabit(adminHabitDto().getId());

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/adminhabits/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNoContent());
    }

}