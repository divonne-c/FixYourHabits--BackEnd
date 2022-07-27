package com.fixyourhabitsbackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "userprofile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column
    private int totalCompletedHabits;

    @Column
    private int totalHabits;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "user_start_date", updatable = false)
    private Date userStartDate = new Date();

    @OneToOne
    FileUploadResponse file;

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.EXTRA)
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.MERGE)
    private List<UserHabit> userHabits = new ArrayList<>();

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.EXTRA)
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.MERGE)
    private List<UserReward> userRewards = new ArrayList<>();


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

    public Date getUserStartDate() {
        return userStartDate;
    }

    public void setUserStartDate(Date userStartDate) {
        this.userStartDate = userStartDate;
    }

    public List<UserHabit> getUserHabits() {
        return userHabits;
    }

    public void setUserHabits(List<UserHabit> userHabits) {
        this.userHabits = userHabits;
    }

    public void addHabit(UserHabit userHabit) {
        this.userHabits.add(userHabit);
    }

    public void removeHabit(UserHabit userHabit) {
        this.userHabits.remove(userHabit);
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
