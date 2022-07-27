package com.fixyourhabitsbackend.controllers;

import com.fixyourhabitsbackend.dtos.AdminProfileDto;
import com.fixyourhabitsbackend.models.AdminHabit;
import com.fixyourhabitsbackend.models.AdminReward;
import com.fixyourhabitsbackend.models.FileUploadResponse;
import com.fixyourhabitsbackend.services.AdminProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/adminprofiles")
public class AdminProfileController {

    @Autowired
    private AdminProfileService adminProfileService;

    @Autowired
    private PhotoController photoController;


    @GetMapping
    @Transactional
    public ResponseEntity<List<AdminProfileDto>> getAllAdmins() {
        return ResponseEntity.ok().body(adminProfileService.getAllAdmins());
    }

    @GetMapping("{adminname}")
    @Transactional
    public ResponseEntity<AdminProfileDto> getAdminById(@PathVariable String adminname) {
        AdminProfileDto getAdmin = adminProfileService.getAdminByUsername(adminname);
        return ResponseEntity.ok().body(getAdmin);
    }

    @GetMapping("{username}/adminhabits")
    public ResponseEntity<List<AdminHabit>> getAdminHabitsWithUserName(@PathVariable String username) {
        List<AdminHabit> adminHabits = adminProfileService.getAdminHabits(username);
        return ResponseEntity.ok().body(adminHabits);
    }

    @GetMapping("{username}/rewards")
    public ResponseEntity<List<AdminReward>> getAdminRewardsWithUserName(@PathVariable String username) {
        List<AdminReward> rewards = adminProfileService.getAdminRewards(username);
        return ResponseEntity.ok().body(rewards);
    }

    @PutMapping("{username}")
    public ResponseEntity<AdminProfileDto> updateUser(@PathVariable String username, @RequestBody AdminProfileDto dto) {
        adminProfileService.updateAdmin(username, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{username}/adminhabits")
    public ResponseEntity<Object> deleteUserHabits(@PathVariable String username) {
        adminProfileService.deleteAllAdminHabits(username);
        return ResponseEntity.noContent().build();
    }

       @DeleteMapping("/{username}/adminrewards")
    public ResponseEntity<Object> deleteAdminHabits(@PathVariable String username) {
        adminProfileService.deleteAllAdminRewards(username);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{username}/photo")
    public void assignPhotoToStudent(@PathVariable String username,
                                     @RequestBody MultipartFile file) {

        FileUploadResponse photo = photoController.singleFileUpload(file);
        adminProfileService.assignPhotoToAdminProfile(photo.getFileName(), username);
    }


}