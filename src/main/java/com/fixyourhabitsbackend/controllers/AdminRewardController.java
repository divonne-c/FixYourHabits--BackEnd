package com.fixyourhabitsbackend.controllers;

import com.fixyourhabitsbackend.dtos.AdminRewardDto;
import com.fixyourhabitsbackend.models.AdminReward;
import com.fixyourhabitsbackend.services.AdminRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("adminrewards")
public class AdminRewardController {

    @Autowired
    private AdminRewardService rewardservice;

    @GetMapping
    public ResponseEntity<List<AdminRewardDto>> getAllRewards() {
        return ResponseEntity.ok().body(rewardservice.getAllRewards());
    }

    @GetMapping("{id}")
    public ResponseEntity<AdminRewardDto> getRewardById(@PathVariable Long id) {
        AdminRewardDto getReward = rewardservice.getRewardById(id);
        return ResponseEntity.ok().body(getReward);
    }

    @PostMapping
    public ResponseEntity<AdminRewardDto> createReward(@RequestBody AdminReward adminReward) {
        final AdminRewardDto rewards= rewardservice.createReward(adminReward);
        final URI location = URI.create("/adminrewards/" + rewards.getId());
        return ResponseEntity.created(location).body(rewards);
    }
    @PutMapping("{id}")
    public ResponseEntity<AdminRewardDto> updateReward(@PathVariable Long id, @RequestBody AdminReward adminReward) {
        AdminRewardDto rewards= rewardservice.updateReward(id, adminReward);
        return ResponseEntity.ok().body(rewards);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AdminReward> deleteReward(@PathVariable Long id) {
        rewardservice.deleteReward(id);
        return ResponseEntity.noContent().build();
    }
}
