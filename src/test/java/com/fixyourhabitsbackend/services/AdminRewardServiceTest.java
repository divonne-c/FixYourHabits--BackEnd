package com.fixyourhabitsbackend.services;

import com.fixyourhabitsbackend.dtos.AdminRewardDto;
import com.fixyourhabitsbackend.models.AdminReward;
import com.fixyourhabitsbackend.repositories.AdminRewardRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes={AdminRewardService.class})
class AdminRewardServiceTest {

    @Autowired
    AdminRewardService adminRewardService;

    @MockBean
    private AdminRewardRepository adminRewardRepository;

    @Test
    void getAllAdminRewards() {
        adminRewardService.getAllRewards();

        Mockito.verify(adminRewardRepository).findAll();
    }

    @Test
    void getAdminRewardById() {
        AdminReward adminReward = new AdminReward();
        AdminRewardDto adminRewardDto = new AdminRewardDto();
        adminReward.setId(1L);
        adminRewardDto.setId(adminReward.getId());

        Mockito.when(adminRewardRepository.findById(adminReward.getId())).thenReturn(Optional.of(adminReward));

        AdminRewardDto result = adminRewardService.getRewardById(adminRewardDto.getId());
        assertEquals(1, result.getId());
    }


    @Test
    void deleteAdminReward() {
        AdminReward adminReward = new AdminReward();
        adminReward.setId(1L);

        Mockito.when(adminRewardRepository.findById(adminReward.getId())).thenReturn(Optional.of(adminReward));

        adminRewardService.deleteReward(adminReward.getId());

        Mockito.verify(adminRewardRepository).deleteById(adminReward.getId());
    }
}