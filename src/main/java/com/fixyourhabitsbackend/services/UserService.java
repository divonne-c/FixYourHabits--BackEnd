package com.fixyourhabitsbackend.services;

import com.fixyourhabitsbackend.dtos.UserDto;
import com.fixyourhabitsbackend.exceptions.UsernameNotFoundException;
import com.fixyourhabitsbackend.models.*;
import com.fixyourhabitsbackend.repositories.AdminProfileRepository;
import com.fixyourhabitsbackend.repositories.UserProfileRepository;
import com.fixyourhabitsbackend.repositories.UserRepository;
import com.fixyourhabitsbackend.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private AdminProfileRepository adminProfileRepository;

    @Autowired
    private AdminProfileService adminProfileService;

    public List<UserDto> getAllUsers() {
        List<UserDto> userDtos = new ArrayList<>();
        List<User> list = userRepository.findAll();

        for (User user : list) {
            userDtos.add(fromUser(user));
        }

        return userDtos;
    }

    public UserDto getUserByUsername(String username) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            return fromUser(user.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public String createUser(UserDto userDto) {
        Optional<User> findUser = userRepository.findById(userDto.getUsername());
       Optional<User> findEmail = userRepository.findUserByEmail(userDto.getEmail());

        if (findUser.isPresent() && findEmail.isPresent()) {
            throw new IllegalStateException("Username or email already in use");
        } else {
            String randomString = RandomStringGenerator.generateAlphaNumeric(20);
            userDto.setApikey(randomString);
            userDto.setEnabled(true);
            User newUser = userRepository.save(toUser(userDto));
            UserProfile userProfile = new UserProfile();

            userProfile.setUser(newUser);
            userProfileRepository.save(userProfile);

            return newUser.getUsername();
        }
    }

    public String createAdmin(UserDto userDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);
        userDto.setEnabled(true);
        User newUser = userRepository.save(toUser(userDto));

        AdminProfile adminProfile = new AdminProfile();

        adminProfile.setUser(newUser);
        adminProfileRepository.save(adminProfile);

        return newUser.getUsername();
    }

    public void updateUser(String username, UserDto userDto) {
        Optional<User> userFound = userRepository.findById(username);

        if (userFound.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        else {
            User newUser = userFound.get();

            newUser.setName(userDto.getName());
            newUser.setEmail(userDto.getEmail());
            newUser.setPassword(userDto.getPassword());

            userRepository.save(newUser);
        }
    }

    public void deleteUser(String username) {
        Optional<User> user = userRepository.findById(username);
        Optional<UserProfile> getUserProfile = userProfileRepository.findUserProfileByUser_Username(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        } else if (getUserProfile.isPresent()) {
         UserProfile userProfile = getUserProfile.get();
         List<UserHabit> userHabits = userProfile.getUserHabits();

            for (UserHabit userHabit : userHabits) {
                userProfile.removeHabit(userHabit);
            }
         
        userRepository.deleteById(username);
        } else {
            adminProfileService.deleteAllAdminHabits(username);
            adminProfileService.deleteAllAdminRewards(username);
            userRepository.deleteById(username);
        }
    }

    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDto userDto = fromUser(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);

        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));

        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);

        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();

        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

    public UserDto fromUser(User user){
        var dto = new UserDto();

        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setEnabled(user.isEnabled());
        dto.setApikey(user.getApikey());
        dto.setAuthorities(user.getAuthorities());

        return dto;
    }

    public User toUser(UserDto userDto) {
        var user = new User();

        user.setUsername(userDto.getUsername());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.getEnabled());
        user.setApikey(userDto.getApikey());

        return user;
    }

}
