package com.fixyourhabitsbackend.repositories;

import com.fixyourhabitsbackend.models.UserReward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRewardRepository extends JpaRepository<UserReward, Long> {
}
