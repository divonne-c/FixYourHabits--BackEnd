package com.fixyourhabitsbackend.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserProfileTest {

    @Test
    void getId() {
        UserProfile userProfile = new UserProfile();

        userProfile.setId(1L);

        Long result = userProfile.getId();
        assertEquals(1, result);
    }

    @Test
    void getUser() {
        UserProfile userProfile = new UserProfile();
        User user = new User();

        userProfile.setUser(user);

        User result = userProfile.getUser();
        User expected = user;
        assertEquals(expected, result);
    }

    @Test
    void getTotalCompletedHabits() {
        UserProfile userProfile = new UserProfile();

        userProfile.setTotalCompletedHabits(1);

        int result = userProfile.getTotalCompletedHabits();
        assertEquals(1, result);
    }

    @Test
    void getUserStartDate() {
        UserProfile userProfile = new UserProfile();

        userProfile.setUserStartDate(new Date());

        Date result = userProfile.getUserStartDate();
        assertEquals(new Date(), result);
    }

    @Test
    void getUserHabits() {
        List<UserHabit> userHabits = new ArrayList<>();
        UserHabit userHabit = new UserHabit();
        userHabits.add(userHabit);

        UserProfile userProfile = new UserProfile();
        userProfile.setUserHabits(userHabits);

        List<UserHabit> result = userProfile.getUserHabits();
        List<UserHabit> expected = userHabits;
        assertEquals(result, expected);
    }

    @Test
    void getFile() {
        UserProfile userProfile = new UserProfile();
        FileUploadResponse file = new FileUploadResponse();

        userProfile.setFile(file);

        FileUploadResponse result = userProfile.getFile();
        FileUploadResponse expected = file;
        assertEquals(expected, result);
    }

    @Test
    void getUserRewards() {
        List<UserReward> userRewards = new ArrayList<>();
        UserReward userReward = new UserReward();
        userRewards.add(userReward);

        UserProfile userProfile = new UserProfile();
        userProfile.setUserRewards(userRewards);

        List<UserReward> result = userProfile.getUserRewards();
        List<UserReward> expected = userRewards;
        assertEquals(result, expected);
    }
}