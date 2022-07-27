package com.fixyourhabitsbackend.services;

import com.fixyourhabitsbackend.dtos.AdminProfileDto;
import com.fixyourhabitsbackend.models.AdminHabit;
import com.fixyourhabitsbackend.models.AdminProfile;
import com.fixyourhabitsbackend.models.AdminReward;
import com.fixyourhabitsbackend.models.User;
import com.fixyourhabitsbackend.repositories.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes={AdminProfileService.class})
class AdminProfileServiceTest {

    @Autowired
    AdminProfileService adminProfileService;

    @MockBean
    private AdminProfileRepository adminProfileRepository;

    @MockBean
    private AdminHabitRepository adminHabitRepository;

    @MockBean
    private AdminRewardRepository adminRewardRepository;

    @MockBean
    private FileUploadRepository uploadRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    void shouldReturnAllAdminProfiles() {
        adminProfileService.getAllAdmins();

        Mockito.verify(adminProfileRepository).findAll();
    }

    @Test
    void shouldGetAdminProfileByAdminname() {
        User user = new User();
        user.setUsername("admin");
        AdminProfile adminProfile = new AdminProfile();
        adminProfile.setUser(user);

        Mockito.when(adminProfileRepository.findAdminProfileByUser_Username(adminProfile.getUser().getUsername())).thenReturn(Optional.of(adminProfile));

        AdminProfileDto result = adminProfileService.getAdminByUsername(adminProfile.getUser().getUsername());
        assertEquals("admin", result.getUser().getUsername());
    }

    @Test
    void getAdminHabitsOfAdminProfile() {
        User user = new User();
        user.setUsername("admin");

        AdminProfile adminProfile = new AdminProfile();
        adminProfile.setUser(user);

        List<AdminHabit> adminHabits = new ArrayList<>();
        adminProfile.setAdminHabits(adminHabits);

        Mockito.when(adminProfileRepository.findAdminProfileByUser_Username(adminProfile.getUser().getUsername())).thenReturn(Optional.of(adminProfile));

        List<AdminHabit> result = adminProfileService.getAdminHabits("admin");
        assertEquals(adminHabits, result);
    }

    @Test
    void getAdminRewardsOfAdminProfile() {
        User user = new User();
        user.setUsername("admin");

        AdminProfile adminProfile = new AdminProfile();
        adminProfile.setUser(user);

        List<AdminReward> adminRewards = new ArrayList<>();
        adminProfile.setAdminRewards(adminRewards);

        Mockito.when(adminProfileRepository.findAdminProfileByUser_Username(adminProfile.getUser().getUsername())).thenReturn(Optional.of(adminProfile));

        List<AdminReward> result = adminProfileService.getAdminRewards("admin");
        assertEquals(adminRewards, result);

    }
}