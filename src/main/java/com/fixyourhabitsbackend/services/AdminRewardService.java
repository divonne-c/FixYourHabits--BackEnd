package com.fixyourhabitsbackend.services;

import com.fixyourhabitsbackend.dtos.AdminRewardDto;
import com.fixyourhabitsbackend.exceptions.RecordNotFoundException;
import com.fixyourhabitsbackend.models.AdminReward;
import com.fixyourhabitsbackend.repositories.AdminRewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminRewardService {

    @Autowired
    private AdminRewardRepository adminRewardRepository;

    public List<AdminRewardDto> getAllRewards() {
        List<AdminRewardDto> adminRewardDtos = new ArrayList<>();
        List<AdminReward> adminRewards = adminRewardRepository.findAll();

        for (AdminReward habit : adminRewards) {
            adminRewardDtos.add(fromReward(habit));
        }

        return adminRewardDtos;
    }
    public AdminRewardDto getRewardById(Long id) {
        var reward = adminRewardRepository.findById(id);
        if (reward.isPresent()) {
            return fromReward(reward.get());
        } else {
            throw new RecordNotFoundException("AdminReward is not found");
        }
    }
    public AdminRewardDto createReward(AdminReward adminReward) {
        final AdminReward savedAdminReward = adminRewardRepository.save(adminReward);
        final AdminRewardDto adminRewardDto = fromReward(savedAdminReward);
        return adminRewardDto;
    }

    public void deleteReward(Long rewardId) {
        Optional<AdminReward> exist = adminRewardRepository.findById(rewardId);

        if (exist.isEmpty()) {
            throw new IllegalStateException("Reward with id " + rewardId + " does not exist");
        }
        adminRewardRepository.deleteById(rewardId);
    }
    public AdminRewardDto updateReward(Long id, AdminReward adminReward) {
        Optional<AdminReward> rewardFound = adminRewardRepository.findById(id);

        if (rewardFound.isEmpty()) {
            throw new RecordNotFoundException("AdminReward not found");
        } else {
            AdminReward newAdminReward = rewardFound.get();
            newAdminReward.setName(adminReward.getName());
            newAdminReward.setType(adminReward.getType());

            adminRewardRepository.save(newAdminReward);
            return fromReward(newAdminReward);
        }
    }

    public static AdminRewardDto fromReward (AdminReward adminReward){
        var dto = new AdminRewardDto();
        dto.setId(adminReward.getId());
        dto.setName(adminReward.getName());
        dto.setType(adminReward.getType());
        dto.setDescription(adminReward.getDescription());

        return dto;
    }

}

