package com.fixyourhabitsbackend.repositories;

import com.fixyourhabitsbackend.models.UserProfile;
import com.fixyourhabitsbackend.models.UserReward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRewardRepository extends JpaRepository<UserReward, Long> {
    Optional<UserReward> findUserRewardByUserProfileAndAndName(UserProfile userProfile, String name);
}
