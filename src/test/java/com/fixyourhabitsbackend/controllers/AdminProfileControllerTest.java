package com.fixyourhabitsbackend.controllers;

import com.fixyourhabitsbackend.dtos.AdminProfileDto;
import com.fixyourhabitsbackend.models.AdminHabit;
import com.fixyourhabitsbackend.models.AdminReward;
import com.fixyourhabitsbackend.models.User;
import com.fixyourhabitsbackend.security.CustomUserDetailsService;
import com.fixyourhabitsbackend.security.JwtUtil;
import com.fixyourhabitsbackend.services.AdminProfileService;
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
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminProfileController.class)
class AdminProfileControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomUserDetailsService customUserDetailsService;

    @MockBean
    AdminProfileService adminService;

    @MockBean
    PhotoController photoController;

    @MockBean
    JwtUtil jwtUtil;

    ///// Test Admin ////
    public AdminProfileDto adminProfileDto() {
        AdminProfileDto adminProfileDto = new AdminProfileDto();
        User user = new User();

        user.setUsername("admin");
        user.setName("admin");
        user.setEmail("admin@hotmail.com");
        user.setPassword("password");

        adminProfileDto.setId(1L);
        adminProfileDto.setUser(user);

        List<AdminHabit> adminHabits = new ArrayList<>();
        adminProfileDto.setAdminHabits(adminHabits);

        List<AdminReward> adminRewards = new ArrayList<>();
        adminProfileDto.setAdminRewards(adminRewards);

        return adminProfileDto;
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAllAdminProfilesAuthorizedWithAdminAuth() throws Exception {

        List<AdminProfileDto> admins = Arrays.asList(adminProfileDto());

        Mockito.when(adminService.getAllAdmins()).thenReturn(admins);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/adminprofiles"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAdminProfileByIdAuthorizedWithAdminAuth() throws Exception {

        Mockito.when(adminService.getAdminByUsername("admin")).thenReturn(adminProfileDto());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/adminprofiles/admin"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void getAllAdminHabits() throws Exception {
        List<AdminHabit> adminHabits = adminProfileDto().getAdminHabits();

        Mockito.when(adminService.getAdminHabits("admin")).thenReturn(adminHabits);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/adminprofiles/admin/adminhabits"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAllAdminRewards() throws Exception {
        List<AdminReward> adminRewards = adminProfileDto().getAdminRewards();

        Mockito.when(adminService.getAdminRewards("admin")).thenReturn(adminRewards);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/adminprofiles/admin/adminrewards"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}