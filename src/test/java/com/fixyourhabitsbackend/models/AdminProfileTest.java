package com.fixyourhabitsbackend.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdminProfileTest {

    @Test
    void getAdminId() {
        AdminProfile adminProfile = new AdminProfile();
        adminProfile.setId(1L);

        Long result = adminProfile.getId();

        assertEquals(1, result);
    }


    @Test
    void getUser() {
        User user = new User();
        user.setUsername("admin");

        AdminProfile adminProfile = new AdminProfile();
        adminProfile.setUser(user);

        User result = adminProfile.getUser();

        User expected = user;

        assertEquals(expected, result);
    }

    @Test
    void getAdminHabits() {
        List<AdminHabit> adminHabits = new ArrayList<>();
        AdminHabit adminHabit = new AdminHabit();
        adminHabits.add(adminHabit);

        AdminProfile adminProfile = new AdminProfile();
        adminProfile.setAdminHabits(adminHabits);

        List<AdminHabit> result = adminProfile.getAdminHabits();
        List<AdminHabit> expected = adminHabits;
        assertEquals(result, expected);
    }

}