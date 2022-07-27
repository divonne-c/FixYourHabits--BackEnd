package com.fixyourhabitsbackend.controllers;

import com.fixyourhabitsbackend.dtos.UserProfileDto;
import com.fixyourhabitsbackend.models.FileUploadResponse;
import com.fixyourhabitsbackend.models.UserHabit;
import com.fixyourhabitsbackend.models.UserReward;
import com.fixyourhabitsbackend.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/userprofiles")
public class UserProfileController {

    @Autowired
    private UserProfileService userService;

    @Autowired
    private PhotoController photoController;

    @GetMapping
    @Transactional
    public ResponseEntity<List<UserProfileDto>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("{username}")
    @Transactional
    public ResponseEntity<UserProfileDto> getUserById(@PathVariable String username) {
        UserProfileDto getUser = userService.getUserByUsername(username);
        return ResponseEntity.ok().body(getUser);
    }

    @GetMapping("{username}/userhabits")
    public ResponseEntity<List<UserHabit>> getUserHabits(@PathVariable String username) {
        List<UserHabit> userHabits = userService.getUserHabitsOfUserProfile(username);
        return ResponseEntity.ok().body(userHabits);
    }

    @GetMapping("{username}/rewards")
    public ResponseEntity<List<UserReward>> getUserRewards(@PathVariable String username) {
        List<UserReward> userRewards = userService.getUserRewardsOfUserProfile(username);
        return ResponseEntity.ok().body(userRewards);
    }

    @PostMapping("/{username}/photo")
    public void assignPhotoToStudent(@PathVariable String username,
                                     @RequestBody MultipartFile file) {
        FileUploadResponse photo = photoController.singleFileUpload(file);
        userService.assignPhotoToUserProfile(photo.getFileName(), username);
    }

    @PutMapping("{username}")
    public ResponseEntity<UserProfileDto> updateUser(@PathVariable String username, @RequestBody UserProfileDto dto) {
        userService.updateUser(username, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{username}/userhabits")
    public ResponseEntity<Object> deleteUserHabits(@PathVariable String username) {
        userService.deleteAllUserHabits(username);
        return ResponseEntity.noContent().build();
    }

   @DeleteMapping("/{username}/userrewards")
    public ResponseEntity<Object> deleteUserRewards(@PathVariable String username) {
        userService.deleteAllUserRewards(username);
        return ResponseEntity.noContent().build();
    }

}
