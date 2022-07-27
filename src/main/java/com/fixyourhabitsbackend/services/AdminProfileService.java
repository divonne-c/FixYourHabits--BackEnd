package com.fixyourhabitsbackend.services;

import com.fixyourhabitsbackend.dtos.AdminProfileDto;
import com.fixyourhabitsbackend.exceptions.RecordNotFoundException;
import com.fixyourhabitsbackend.exceptions.UsernameNotFoundException;
import com.fixyourhabitsbackend.models.*;
import com.fixyourhabitsbackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminProfileService {

    @Autowired
    private AdminProfileRepository adminProfileRepository;

    @Autowired
    private AdminHabitRepository adminHabitRepository;

    @Autowired
    private AdminRewardRepository adminRewardRepository;

    @Autowired
    private FileUploadRepository uploadRepository;

    @Autowired
    private UserRepository userRepository;

    public List<AdminProfileDto> getAllAdmins() {
        List<AdminProfileDto> adminProfileDtos = new ArrayList<>();
        List<AdminProfile> list = adminProfileRepository.findAll();

        for (AdminProfile adminProfile : list) {
            adminProfileDtos.add(fromAdmin(adminProfile));
        }

        return adminProfileDtos;
    }

    public AdminProfileDto getAdminByUsername(String username) {
        Optional<AdminProfile> admin = adminProfileRepository.findAdminProfileByUser_Username(username);
        if (admin.isPresent()) {
            return fromAdmin(admin.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public List<AdminHabit> getAdminHabits(String username) {
        Optional<AdminProfile> admin = adminProfileRepository.findAdminProfileByUser_Username(username);

        if (admin.isPresent()) {
            AdminProfile adminProfile = admin.get();

            return adminProfile.getAdminHabits();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public List<AdminReward> getAdminRewards(String username) {
        Optional<AdminProfile> admin = adminProfileRepository.findAdminProfileByUser_Username(username);

        if (admin.isPresent()) {
            AdminProfile adminProfile = admin.get();

            return adminProfile.getAdminRewards();
        } else {
            throw new RecordNotFoundException("can't find");
        }
    }

    public void updateAdmin(String username, AdminProfileDto adminProfileDto) {
        Optional<AdminProfile> adminProfileFound = adminProfileRepository.findAdminProfileByUser_Username(username);
        Optional<User> userFound = userRepository.findById(username);

        if (!adminProfileFound.isPresent() && !userFound.isPresent()) {
            throw new UsernameNotFoundException(username);
        } else {
            AdminProfile newAdminProfile = adminProfileFound.get();
            User newUser = userFound.get();

            newUser.setName(adminProfileDto.getUser().getName());
            newUser.setPassword(adminProfileDto.getUser().getPassword());

            newAdminProfile.setAdminHabits(adminProfileDto.getAdminHabits());
            newAdminProfile.setAdminRewards(adminProfileDto.getAdminRewards());
            newAdminProfile.setFile(adminProfileDto.getFile());

            userRepository.save(newUser);
            adminProfileRepository.save(newAdminProfile);
        }
    }

    public void deleteAllAdminHabits(String username) {
        Optional<AdminProfile> getAdminProfile = adminProfileRepository.findAdminProfileByUser_Username(username);

        if (getAdminProfile.isEmpty()) {
            throw new UsernameNotFoundException(username);
        } else {

            AdminProfile adminProfile = getAdminProfile.get();

            List<AdminHabit> list = adminProfile.getAdminHabits();

            for (AdminHabit adminHabit : list) {
                adminHabitRepository.delete(adminHabit);
            }
        }
    }

    public void deleteAllAdminRewards(String username) {
        Optional<AdminProfile> getAdminProfile = adminProfileRepository.findAdminProfileByUser_Username(username);

        if (getAdminProfile.isEmpty()) {
            throw new UsernameNotFoundException(username);
        } else {

            AdminProfile adminProfile = getAdminProfile.get();

            List<AdminReward> list = adminProfile.getAdminRewards();

            for (AdminReward adminReward : list) {
                adminRewardRepository.delete(adminReward);
            }
        }
    }

    public void assignPhotoToAdminProfile(String name, String username) {
        Optional<AdminProfile> findAdminProfile = adminProfileRepository.findAdminProfileByUser_Username(username);
        Optional<FileUploadResponse> fileUploadResponse = uploadRepository.findByFileName(name);

        if (findAdminProfile.isPresent() && fileUploadResponse.isPresent()) {
            FileUploadResponse photo = fileUploadResponse.get();
            AdminProfile adminProfile = findAdminProfile.get();

            adminProfile.setFile(photo);

            adminProfileRepository.save(adminProfile);
        }
    }

    public static AdminProfileDto fromAdmin(AdminProfile admin){
        var dto = new AdminProfileDto();

        dto.setId(admin.getId());
        dto.setUser(admin.getUser());
        dto.setAdminHabits(admin.getAdminHabits());
        dto.setAdminRewards(admin.getAdminRewards());
        dto.setFile(admin.getFile());

        return dto;
    }
}
