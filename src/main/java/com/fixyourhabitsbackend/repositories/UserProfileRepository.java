package com.fixyourhabitsbackend.repositories;

import com.fixyourhabitsbackend.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findUserProfileByUser_Username(String username);
    Optional<UserProfile> deleteUserProfileByUser_Username(String username);
}
