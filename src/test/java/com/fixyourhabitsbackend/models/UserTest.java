package com.fixyourhabitsbackend.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void getUsername() {
        User user = new User();

        user.setUsername("user");

        String result = user.getUsername();
        assertEquals("user", result);
    }

    @Test
    void getName() {
        User user = new User();

        user.setName("user");

        String result = user.getName();
        assertEquals("user", result);
    }

    @Test
    void getEmail() {
        User user = new User();

        user.setEmail("user@hotmail.com");

        String result = user.getEmail();
        assertEquals("user@hotmail.com", result);
    }

    @Test
    void getPassword() {
        User user = new User();

        user.setPassword("password");

        String result = user.getPassword();
        assertEquals("password", result);
    }

    @Test
    void isEnabled() {
        User user = new User();

        user.setEnabled(true);

        boolean result = user.isEnabled();
        assertEquals(true, result);
    }

    @Test
    void getApikey() {
        User user = new User();

        user.setApikey("SMZqsxtn18Bz0hZK0vBe");

        String result = user.getApikey();
        assertEquals("SMZqsxtn18Bz0hZK0vBe", result);
    }

    @Test
    void getAuthorities() {
        Set<Authority> authorities = new HashSet<>();
        Authority authority = new Authority();
        authorities.add(authority);

        User user = new User();

        user.addAuthority(authority);

        Set<Authority> result = user.getAuthorities();
        Set<Authority> expected = authorities;
        assertEquals(expected, result);
    }

    @Test
    void getUserProfileList() {
        List<UserProfile> userProfileList = new ArrayList<>();
        UserProfile userProfile = new UserProfile();
        userProfileList.add(userProfile);

        User user = new User();

        user.setUserProfileList(userProfileList);

        List<UserProfile> result = user.getUserProfileList();
        List<UserProfile> expected = userProfileList;
        assertEquals(expected, result);
    }

    @Test
    void getAdminProfileList() {
        List<AdminProfile> adminProfileList = new ArrayList<>();
        AdminProfile adminProfile = new AdminProfile();
        adminProfileList.add(adminProfile);

        User user = new User();

        user.setAdminProfileList(adminProfileList);

        List<AdminProfile> result = user.getAdminProfileList();
        List<AdminProfile> expected = adminProfileList;
        assertEquals(expected, result);
    }
}