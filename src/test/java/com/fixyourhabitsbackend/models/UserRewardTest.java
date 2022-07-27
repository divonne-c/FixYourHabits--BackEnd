package com.fixyourhabitsbackend.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRewardTest {

    @Test
    void getId() {
        UserReward userReward = new UserReward();
        userReward.setId(1L);

        Long result = userReward.getId();

        assertEquals(1, result);
    }

    @Test
    void getName() {
        UserReward userReward = new UserReward();
        userReward.setName("gold");

        String result = userReward.getName();

        assertEquals("gold", result);
    }

    @Test
    void getType() {
        UserReward userReward = new UserReward();
        userReward.setType("sport");

        String result = userReward.getType();

        assertEquals("sport", result);
    }
}