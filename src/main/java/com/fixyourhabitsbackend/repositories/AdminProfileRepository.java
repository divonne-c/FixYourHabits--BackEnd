package com.fixyourhabitsbackend.repositories;

import com.fixyourhabitsbackend.models.AdminProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminProfileRepository extends JpaRepository<AdminProfile, Long> {
    Optional<AdminProfile> findAdminProfileByUser_Username(String username);
}
