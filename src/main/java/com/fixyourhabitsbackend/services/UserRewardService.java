package com.fixyourhabitsbackend.services;

import com.fixyourhabitsbackend.dtos.UserRewardDto;
import com.fixyourhabitsbackend.exceptions.RecordNotFoundException;
import com.fixyourhabitsbackend.models.UserReward;
import com.fixyourhabitsbackend.repositories.UserProfileRepository;
import com.fixyourhabitsbackend.repositories.UserRewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRewardService {

    @Autowired
    private UserRewardRepository userRewardRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public List<UserRewardDto> getAllRewards() {
        List<UserRewardDto> userRewardDtos = new ArrayList<>();
        List<UserReward> userRewards = userRewardRepository.findAll();

        for (UserReward habit : userRewards) {
            userRewardDtos.add(fromReward(habit));
        }

        return userRewardDtos;
    }

    public UserRewardDto getRewardById(Long id) {
        var reward = userRewardRepository.findById(id);
        if (reward.isPresent()) {
            return fromReward(reward.get());
        } else {
            throw new RecordNotFoundException("UserReward is not found");
        }
    }

    public UserRewardDto createReward(UserReward userReward) {
        final UserReward savedUserReward = userRewardRepository.save(userReward);
        final UserRewardDto userRewardDto = fromReward(savedUserReward);
        return userRewardDto;
    }

    public void deleteReward(Long rewardId) {
        Optional<UserReward> exist = userRewardRepository.findById(rewardId);

        if (!exist.isPresent()) {
            throw new IllegalStateException("reward with id " + rewardId + " does not exist");
        }
        userRewardRepository.deleteById(rewardId);
    }

    public UserRewardDto updateReward(Long id, UserReward userReward) {
        Optional<UserReward> rewardFound = userRewardRepository.findById(id);

        if (!rewardFound.isPresent()) {
            throw new RecordNotFoundException("UserReward not found");
        } else {
            UserReward newUserReward = rewardFound.get();
            newUserReward.setName(userReward.getName());
            newUserReward.setType(userReward.getType());
            newUserReward.setDescription(userReward.getDescription());
            newUserReward.setUserProfile(userReward.getUserProfile());

            userRewardRepository.save(newUserReward);
            return fromReward(newUserReward);
        }
    }

    public static UserRewardDto fromReward (UserReward userReward){
        var dto = new UserRewardDto();
        dto.setId(userReward.getId());
        dto.setName(userReward.getName());
        dto.setType(userReward.getType());
        dto.setDescription(userReward.getDescription());
//        dto.setUserProfile(userReward.getUserProfile());

        return dto;
    }

}