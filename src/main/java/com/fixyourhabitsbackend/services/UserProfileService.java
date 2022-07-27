package com.fixyourhabitsbackend.services;

import com.fixyourhabitsbackend.dtos.UserProfileDto;
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
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserHabitRepository userHabitRepository;

    @Autowired
    private UserRewardRepository userRewardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRewardRepository adminRewardRepository;

    @Autowired
    private FileUploadRepository uploadRepository;

    public List<UserProfileDto> getAllUsers() {
        List<UserProfileDto> userProfileDtos = new ArrayList<>();
        List<UserProfile> list = userProfileRepository.findAll();

        for (UserProfile userProfile : list) {
            userProfileDtos.add(fromUser(userProfile));
        }

        return userProfileDtos;
    }

    public UserProfileDto getUserByUsername(String username) {
        Optional<UserProfile> user = userProfileRepository.findUserProfileByUser_Username(username);
        if (user.isPresent()) {
            return fromUser(user.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public UserProfileDto getUserById(Long id) {
        Optional<UserProfile> user = userProfileRepository.findById(id);
        if (user.isPresent()) {
            return fromUser(user.get());
        } else {
            throw new RecordNotFoundException("Can't find id: " + id);
        }
    }

    public void deleteAllUserHabits(String username) {
        Optional<UserProfile> getUserProfile = userProfileRepository.findUserProfileByUser_Username(username);

        if (getUserProfile.isEmpty()) {
            throw new UsernameNotFoundException(username);
        } else {

            UserProfile userProfile = getUserProfile.get();

            List<UserHabit> list = userProfile.getUserHabits();

            for (UserHabit userHabit : list) {
                userHabitRepository.delete(userHabit);
            }
        }
    }


    public void deleteAllUserRewards(String username) {
        Optional<UserProfile> getUserProfile = userProfileRepository.findUserProfileByUser_Username(username);

        if (getUserProfile.isEmpty()) {
            throw new UsernameNotFoundException(username);
        } else {

            UserProfile userProfile = getUserProfile.get();

            List<UserReward> list = userProfile.getUserRewards();

            for (UserReward userReward : list) {
                userRewardRepository.delete(userReward);
            }
        }
    }



    public List<UserHabit> getUserHabitsOfUserProfile(String username) {
        Optional<UserProfile> user = userProfileRepository.findUserProfileByUser_Username(username);

        if (user.isPresent()) {
            UserProfile userProfile = user.get();

            return userProfile.getUserHabits();
        } else {
            throw new RecordNotFoundException("can't find");
        }
    }

    public List<UserReward> getUserRewardsOfUserProfile(String username) {
        Optional<UserProfile> user = userProfileRepository.findUserProfileByUser_Username(username);

        if (user.isPresent()) {
            UserProfile userProfile = user.get();

            return userProfile.getUserRewards();
        } else {
            throw new RecordNotFoundException("can't find");
        }
    }

    // Update user
    public void updateUser(String username, UserProfileDto userDto) {
        Optional<UserProfile> userProfileFound = userProfileRepository.findUserProfileByUser_Username(username);
        Optional<User> userFound = userRepository.findById(username);

        if (!userProfileFound.isPresent() && !userFound.isPresent()) {
            throw new UsernameNotFoundException(username);
        } else {
            UserProfile newUserProfile = userProfileFound.get();
            User newUser = userFound.get();

            newUser.setName(userDto.getUser().getName());
            newUser.setEmail(userDto.getUser().getEmail());
            newUser.setPassword(userDto.getUser().getPassword());

            newUserProfile.setTotalCompletedHabits(userDto.getTotalCompletedHabits());
            newUserProfile.setUserHabits(userDto.getUserHabits());
            newUserProfile.setFile(userDto.getFile());
            newUserProfile.setUserRewards(userDto.getUserRewards());
            newUserProfile.setTotalHabits(userDto.getTotalHabits());

            userRepository.save(newUser);
            userProfileRepository.save(newUserProfile);
        }
    }

    public void assignPhotoToUserProfile(String name, String username) {
        Optional<UserProfile> findUserProfile = userProfileRepository.findUserProfileByUser_Username(username);
        Optional<FileUploadResponse> fileUploadResponse = uploadRepository.findByFileName(name);

        if (findUserProfile.isPresent() && fileUploadResponse.isPresent()) {
            FileUploadResponse photo = fileUploadResponse.get();
            UserProfile userProfile = findUserProfile.get();

            userProfile.setFile(photo);

            userProfileRepository.save(userProfile);
        }
    }
    public UserProfileDto fromUser(UserProfile user){

        var dto = new UserProfileDto();

        dto.setId(user.getId());
        dto.setUser(user.getUser());
        dto.setUserStartDate(user.getUserStartDate());
        dto.setTotalCompletedHabits(user.getTotalCompletedHabits());
        dto.setTotalHabits(user.getTotalHabits());
        dto.setUserHabits(user.getUserHabits());
        dto.setFile(user.getFile());
        dto.setUserRewards(user.getUserRewards());

        return dto;
    }
}
