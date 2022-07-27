package com.fixyourhabitsbackend.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdminHabitTest {

    @Test
    void shouldReturnAdminHabitId() {
        AdminHabit adminHabit = new AdminHabit();
        adminHabit.setId(1L);

        Long result = adminHabit.getId();

        assertEquals(1, result);
    }

    @Test
    void shouldReturnAdminHabitName() {
        AdminHabit adminHabit = new AdminHabit();
        adminHabit.setName("Workout");

        String result = adminHabit.getName();

        assertEquals("Workout", result);
    }

    @Test
    void shouldReturnAdminHabitType() {
        AdminHabit adminHabit = new AdminHabit();
        adminHabit.setType("sport");

        String result = adminHabit.getType();

        assertEquals("sport", result);
    }

    @Test
    void shouldNotReturnAdminHabitAdminProfile() {
        AdminProfile adminProfile = new AdminProfile();
        AdminHabit adminHabit = new AdminHabit();

        adminHabit.setAdminProfile(adminProfile);

        AdminProfile expected = adminHabit.getAdminProfile();

        assertEquals(expected, adminProfile);
    }


}