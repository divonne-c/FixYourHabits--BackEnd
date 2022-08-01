package com.fixyourhabitsbackend.services;

import com.fixyourhabitsbackend.dtos.UserRewardDto;
import com.fixyourhabitsbackend.models.UserReward;
import com.fixyourhabitsbackend.repositories.UserProfileRepository;
import com.fixyourhabitsbackend.repositories.UserRewardRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes={UserRewardService.class})
class UserRewardServiceTest {

    @Autowired
    UserRewardService userRewardService;

    @MockBean
    private UserRewardRepository userRewardRepository;

    @Test
    void getAllUserRewards() {
        userRewardService.getAllRewards();

        Mockito.verify(userRewardRepository).findAll();
    }

    @Test
    void getUserRewardById() {
        UserReward userReward = new UserReward();
        UserRewardDto userRewardDto = new UserRewardDto();
        userReward.setId(1L);
        userRewardDto.setId(userReward.getId());

        Mockito.when(userRewardRepository.findById(userReward.getId())).thenReturn(Optional.of(userReward));

        UserRewardDto result = userRewardService.getRewardById(userRewardDto.getId());
        assertEquals(1, result.getId());
    }


    @Test
    void deleteUserReward() {
        UserReward userReward = new UserReward();
        userReward.setId(1L);

        Mockito.when(userRewardRepository.findById(userReward.getId())).thenReturn(Optional.of(userReward));

        userRewardService.deleteReward(userReward.getId());

        Mockito.verify(userRewardRepository).deleteById(userReward.getId());
    }
}