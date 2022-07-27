package com.fixyourhabitsbackend.repositories;

import com.fixyourhabitsbackend.models.AdminReward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRewardRepository extends JpaRepository<AdminReward, Long> {
    Optional<AdminReward> findByName(String name);
}
