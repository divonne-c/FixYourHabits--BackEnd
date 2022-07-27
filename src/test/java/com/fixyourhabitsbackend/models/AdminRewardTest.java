package com.fixyourhabitsbackend.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdminRewardTest {


    @Test
    void getId() {
        AdminReward adminReward = new AdminReward();
        adminReward.setId(1L);

        Long result = adminReward.getId();

        assertEquals(1, result);
    }

    @Test
    void getName() {
        AdminReward adminReward = new AdminReward();
        adminReward.setName("silver");

        String result = adminReward.getName();

        assertEquals("silver", result);
    }

    @Test
    void getType() {
        AdminReward adminReward = new AdminReward();
        adminReward.setType("sport");

        String result = adminReward.getType();

        assertEquals("sport", result);
    }

    @Test
    void getAdminProfile() {
        AdminProfile adminProfile = new AdminProfile();

        AdminReward adminReward = new AdminReward();
        adminReward.setAdminProfile(adminProfile);

        AdminProfile result = adminReward.getAdminProfile();
        AdminProfile expected = adminProfile;

        assertEquals(expected, result);
    }
}