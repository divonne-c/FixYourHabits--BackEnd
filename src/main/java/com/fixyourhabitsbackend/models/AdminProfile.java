package com.fixyourhabitsbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "adminprofile")
public class AdminProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToOne
    FileUploadResponse file;

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.EXTRA)
    @OneToMany(mappedBy = "adminProfile", cascade = CascadeType.MERGE)
    private List<AdminHabit> adminHabits = new ArrayList<>();

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.EXTRA)
    @OneToMany(mappedBy = "adminProfile", cascade = CascadeType.MERGE)
    private List<AdminReward> adminRewards = new ArrayList<>();

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

    public void removeHabit(AdminHabit adminHabit) {
        this.adminHabits.remove(adminHabit);
    }

    public FileUploadResponse getFile() {
        return file;
    }

    public void setFile(FileUploadResponse file) {
        this.file = file;
    }
}
