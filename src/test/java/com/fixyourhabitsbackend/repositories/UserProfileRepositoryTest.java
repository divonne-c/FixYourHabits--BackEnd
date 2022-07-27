package com.fixyourhabitsbackend.repositories;

import com.fixyourhabitsbackend.models.User;
import com.fixyourhabitsbackend.models.UserProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserProfileRepositoryTest {


    @Autowired
    private UserProfileRepository userProfileRepository;

    @Test
    void shouldFindUserProfileByUsername() {
        UserProfile userProfile = new UserProfile();
        User user = new User();
        user.setUsername("user");
        userProfile.setUser(user);

        Optional<UserProfile> found = userProfileRepository.findUserProfileByUser_Username("user");
        UserProfile result = found.get();

        assertEquals("user", result.getUser().getUsername());
    }
}