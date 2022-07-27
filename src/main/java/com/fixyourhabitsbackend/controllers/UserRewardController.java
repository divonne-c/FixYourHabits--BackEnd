package com.fixyourhabitsbackend.controllers;

import com.fixyourhabitsbackend.dtos.UserRewardDto;
import com.fixyourhabitsbackend.models.UserReward;
import com.fixyourhabitsbackend.services.UserRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("userrewards")
public class UserRewardController {

    @Autowired
    private UserRewardService rewardservice;

    @GetMapping
    public ResponseEntity<List<UserRewardDto>> getAllRewards() {
        return ResponseEntity.ok().body(rewardservice.getAllRewards());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserRewardDto> getRewardById(@PathVariable Long id) {
        UserRewardDto getReward = rewardservice.getRewardById(id);
        return ResponseEntity.ok().body(getReward);
    }

    @PostMapping
    public ResponseEntity<UserRewardDto> createReward(@RequestBody UserReward adminReward) {
        final UserRewardDto rewards= rewardservice.createReward(adminReward);
        final URI location = URI.create("/rewards/" + rewards.getId());
        return ResponseEntity.created(location).body(rewards);
    }
}
