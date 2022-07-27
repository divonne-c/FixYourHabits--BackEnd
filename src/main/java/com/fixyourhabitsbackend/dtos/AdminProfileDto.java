package com.fixyourhabitsbackend.dtos;

import com.fixyourhabitsbackend.models.AdminHabit;
import com.fixyourhabitsbackend.models.AdminReward;
import com.fixyourhabitsbackend.models.FileUploadResponse;
import com.fixyourhabitsbackend.models.User;

import java.util.List;

public class AdminProfileDto {
    private Long id;
    private User user;
    FileUploadResponse file;
    private List<AdminReward> adminRewards;
    private List<AdminHabit> adminHabits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<AdminHabit> getAdminHabits() {
        return adminHabits;
    }

    public void setAdminHabits(List<AdminHabit> adminHabits) {
        this.adminHabits = adminHabits;
    }

    public List<AdminReward> getAdminRewards() {
        return adminRewards;
    }

    public void setAdminRewards(List<AdminReward> adminRewards) {
        this.adminRewards = adminRewards;
    }

    public FileUploadResponse getFile() {
        return file;
    }

    public void setFile(FileUploadResponse file) {
        this.file = file;
    }
}
