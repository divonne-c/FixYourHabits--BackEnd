package com.fixyourhabitsbackend.dtos;

import com.fixyourhabitsbackend.models.FileUploadResponse;
import com.fixyourhabitsbackend.models.User;
import com.fixyourhabitsbackend.models.UserHabit;
import com.fixyourhabitsbackend.models.UserReward;

import java.util.Date;
import java.util.List;

public class UserProfileDto {
    private Long id;
    private User user;
    private Date userStartDate = new Date();
    private int totalCompletedHabits;
    private int totalHabits;
    FileUploadResponse file;
    private List<UserHabit> userHabits;
    private List<UserReward> userRewards;

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

    public Date getUserStartDate() {
        return userStartDate;
    }

    public void setUserStartDate(Date userStartDate) {
        this.userStartDate = userStartDate;
    }

    public int getTotalCompletedHabits() {
        return totalCompletedHabits;
    }

    public void setTotalCompletedHabits(int totalCompletedHabits) {
        this.totalCompletedHabits = totalCompletedHabits;
    }

    public int getTotalHabits() {
        return totalHabits;
    }

    public void setTotalHabits(int totalHabits) {
        this.totalHabits = totalHabits;
    }

    public List<UserHabit> getUserHabits() {
        return userHabits;
    }

    public void setUserHabits(List<UserHabit> userHabits) {
        this.userHabits = userHabits;
    }

    public FileUploadResponse getFile() {
        return file;
    }

    public void setFile(FileUploadResponse file) {
        this.file = file;
    }

    public List<UserReward> getUserRewards() {
        return userRewards;
    }

    public void setUserRewards(List<UserReward> userRewards) {
        this.userRewards = userRewards;
    }
}
