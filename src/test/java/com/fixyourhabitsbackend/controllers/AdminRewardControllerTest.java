package com.fixyourhabitsbackend.controllers;

import com.fixyourhabitsbackend.dtos.AdminRewardDto;
import com.fixyourhabitsbackend.security.CustomUserDetailsService;
import com.fixyourhabitsbackend.security.JwtUtil;
import com.fixyourhabitsbackend.services.AdminRewardService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminRewardController.class)
class AdminRewardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomUserDetailsService customUserDetailsService;

    @MockBean
    AdminRewardService adminRewardService;

    @MockBean
    JwtUtil jwtUtil;

    public AdminRewardDto adminRewardDto() {
        AdminRewardDto adminRewardDto = new AdminRewardDto();

        adminRewardDto.setId(1L);
        adminRewardDto.setName("Workout");
        adminRewardDto.setType("Sport");

        return adminRewardDto;
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAllAdminRewards() throws Exception {
        List<AdminRewardDto> adminRewardDtos = Arrays.asList(adminRewardDto());

        Mockito.when(adminRewardService.getAllRewards()).thenReturn(adminRewardDtos);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/adminrewards"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAdminRewardById() throws Exception {

        Mockito.when(adminRewardService.getRewardById(1L)).thenReturn(adminRewardDto());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/adminrewards/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Workout")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteAdminRewardWithAdminAuthorization() throws Exception {

        Mockito.doNothing().when(adminRewardService).deleteReward(adminRewardDto().getId());

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/adminrewards/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNoContent());
    }

}