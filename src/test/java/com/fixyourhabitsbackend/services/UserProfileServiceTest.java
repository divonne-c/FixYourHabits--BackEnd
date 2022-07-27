package com.fixyourhabitsbackend.services;

import com.fixyourhabitsbackend.dtos.UserProfileDto;
import com.fixyourhabitsbackend.models.User;
import com.fixyourhabitsbackend.models.UserHabit;
import com.fixyourhabitsbackend.models.UserProfile;
import com.fixyourhabitsbackend.models.UserReward;
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
@ContextConfiguration(classes={UserProfileService.class})
class UserProfileServiceTest {

    @Autowired
    UserProfileService userProfileService;

    @MockBean
    private UserProfileRepository userProfileRepository;

    @MockBean
    private UserHabitRepository userHabitRepository;

    @MockBean
    private UserRewardRepository userRewardRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private FileUploadRepository uploadRepository;

    @Test
    void shouldReturnAllUserProfiles() {
        userProfileService.getAllUsers();

        Mockito.verify(userProfileRepository).findAll();
    }

    @Test
    void shouldGetUserProfileByUsername() {
        User user = new User();
        user.setUsername("user");
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);

        Mockito.when(userProfileRepository.findUserProfileByUser_Username(userProfile.getUser().getUsername())).thenReturn(Optional.of(userProfile));

        UserProfileDto result = userProfileService.getUserByUsername(userProfile.getUser().getUsername());
        assertEquals("user", result.getUser().getUsername());
    }

    @Test
    void shouldGetUserProfileById() {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);

        Mockito.when(userProfileRepository.findById(userProfile.getId())).thenReturn(Optional.of(userProfile));

        UserProfileDto result = userProfileService.getUserById(userProfile.getId());
        assertEquals(1, result.getId());
    }


    @Test
    void getUserHabitsOfUserProfile() {
        User user = new User();
        user.setUsername("user");

        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);

        List<UserHabit> userHabits = new ArrayList<>();
        userProfile.setUserHabits(userHabits);

        Mockito.when(userProfileRepository.findUserProfileByUser_Username(user.getUsername())).thenReturn(Optional.of(userProfile));

        List<UserHabit> result = userProfileService.getUserHabitsOfUserProfile("user");
        assertEquals(userHabits, result);
    }

    @Test
    void getUserRewardsOfUserProfile() {
        User user = new User();
        user.setUsername("user");

        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);

        List<UserReward> userRewards = new ArrayList<>();
        userProfile.setUserRewards(userRewards);

        Mockito.when(userProfileRepository.findUserProfileByUser_Username(user.getUsername())).thenReturn(Optional.of(userProfile));

        List<UserReward> result = userProfileService.getUserRewardsOfUserProfile("user");
        assertEquals(userRewards, result);

    }
}