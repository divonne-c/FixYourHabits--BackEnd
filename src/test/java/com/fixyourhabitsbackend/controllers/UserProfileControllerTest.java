package com.fixyourhabitsbackend.controllers;

import com.fixyourhabitsbackend.dtos.UserProfileDto;
import com.fixyourhabitsbackend.models.User;
import com.fixyourhabitsbackend.models.UserHabit;
import com.fixyourhabitsbackend.models.UserReward;
import com.fixyourhabitsbackend.security.CustomUserDetailsService;
import com.fixyourhabitsbackend.security.JwtUtil;
import com.fixyourhabitsbackend.services.UserProfileService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserProfileController.class)
class UserProfileControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomUserDetailsService customUserDetailsService;

    @MockBean
    UserProfileService userService;

    @MockBean
    PhotoController photoController;

    @MockBean
    JwtUtil jwtUtil;

    ///// Test User ////
    public UserProfileDto userProfileDto() {
        UserProfileDto userProfileDto = new UserProfileDto();
        User user = new User();

        user.setUsername("user");
        user.setName("user");
        user.setEmail("user@hotmail.com");
        user.setPassword("password");

        userProfileDto.setId(1L);
        userProfileDto.setUser(user);
        userProfileDto.setUserStartDate(new Date());
        userProfileDto.setFile(null);

        List<UserHabit> userHabits = new ArrayList<>();
        userProfileDto.setUserHabits(userHabits);

        List<UserReward> userRewards = new ArrayList<>();
        userProfileDto.setUserRewards(userRewards);

        return userProfileDto;
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void getAllUserProfilesAuthorizedWithAdminAuth() throws Exception {

        List<UserProfileDto> users = Arrays.asList(userProfileDto());

        Mockito.when(userService.getAllUsers()).thenReturn(users);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/userprofiles"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getUserProfileByIdAuthorizedWithUserAuth() throws Exception {

        Mockito.when(userService.getUserByUsername("user")).thenReturn(userProfileDto());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/userprofiles/user"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getAllUserHabits() throws Exception {
        List<UserHabit> userHabits = userProfileDto().getUserHabits();

        Mockito.when(userService.getUserHabitsOfUserProfile("user")).thenReturn(userHabits);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/userprofiles/user/userhabits"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getAllUserRewards() throws Exception {
        List<UserReward> userRewards = userProfileDto().getUserRewards();

        Mockito.when(userService.getUserRewardsOfUserProfile("user")).thenReturn(userRewards);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/userprofiles/user/rewards"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}