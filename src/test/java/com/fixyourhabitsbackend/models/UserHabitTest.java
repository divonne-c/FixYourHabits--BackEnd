package com.fixyourhabitsbackend.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserHabitTest {

    @Test
    void getId() {
        UserHabit userHabit = new UserHabit();

        userHabit.setId(1L);

        Long result = userHabit.getId();
        assertEquals(1, result);
    }

    @Test
    void getName() {
        UserHabit userHabit = new UserHabit();

        userHabit.setName("workout");

        String result = userHabit.getName();
        assertEquals("workout", result);
    }

    @Test
    void getType() {
        UserHabit userHabit = new UserHabit();

        userHabit.setType("sport");

        String result = userHabit.getType();
        assertEquals("sport", result);
    }


    @Test
    void isCompleted() {
        UserHabit userHabit = new UserHabit();

        userHabit.setCompleted(true);

        boolean result = userHabit.isCompleted();
        assertEquals(true, result);
    }


    @Test
    void getUserProfile() {
        UserProfile userProfile = new UserProfile();
        UserHabit userHabit = new UserHabit();

        userHabit.setUserProfile(userProfile);

        UserProfile result = userHabit.getUserProfile();
        UserProfile expected = userProfile;
        assertEquals(expected, result);
    }

}