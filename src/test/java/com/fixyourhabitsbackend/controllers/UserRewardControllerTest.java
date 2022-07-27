package com.fixyourhabitsbackend.controllers;

import com.fixyourhabitsbackend.dtos.UserRewardDto;
import com.fixyourhabitsbackend.security.CustomUserDetailsService;
import com.fixyourhabitsbackend.security.JwtUtil;
import com.fixyourhabitsbackend.services.UserRewardService;
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


@WebMvcTest(UserRewardController.class)
class UserRewardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomUserDetailsService customUserDetailsService;

    @MockBean
    UserRewardService userRewardService;

    @MockBean
    JwtUtil jwtUtil;

    public UserRewardDto userRewardDto() {
        UserRewardDto userRewardDto = new UserRewardDto();

        userRewardDto.setId(1L);
        userRewardDto.setName("Workout");
        userRewardDto.setType("Sport");

        return userRewardDto;
    }

    @Test
    @WithMockUser(roles = "USER")
    void getAllUserRewards() throws Exception {
        List<UserRewardDto> userRewardDtos = Arrays.asList(userRewardDto());

        Mockito.when(userRewardService.getAllRewards()).thenReturn(userRewardDtos);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/userrewards"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getUserRewardById() throws Exception {

        Mockito.when(userRewardService.getRewardById(1L)).thenReturn(userRewardDto());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/userrewards/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Workout")));
    }

}