package com.fixyourhabitsbackend.services;

import com.fixyourhabitsbackend.dtos.UserDto;
import com.fixyourhabitsbackend.models.User;
import com.fixyourhabitsbackend.repositories.AdminProfileRepository;
import com.fixyourhabitsbackend.repositories.UserProfileRepository;
import com.fixyourhabitsbackend.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes={UserService.class})
class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserProfileRepository userProfileRepository;

    @MockBean
    private AdminProfileRepository adminProfileRepository;

    @MockBean
    private UserProfileService userProfileService;

    @MockBean
    private AdminProfileService adminProfileService;


    @Test
    void getAllUsers() {
        userService.getAllUsers();

        Mockito.verify(userRepository).findAll();
    }

    @Test
    void shouldGetUserByUsername() {
        User user = new User();
        UserDto userDto = new UserDto();
        user.setUsername("user");
        userDto.setUsername(user.getUsername());

        Mockito.when(userRepository.findById(user.getUsername())).thenReturn(Optional.of(user));

        UserDto result = userService.getUserByUsername(userDto.getUsername());
        assertEquals("user", result.getUsername());
    }


    @Test
    void deleteUser() {
        User user = new User();
        user.setUsername("user");

        Mockito.when(userRepository.findById(user.getUsername())).thenReturn(Optional.of(user));

        userService.deleteUser(user.getUsername());

        Mockito.verify(userRepository).deleteById(user.getUsername());
    }

}