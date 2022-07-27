package com.fixyourhabitsbackend.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorityTest {

    @Test
    void getUsername() {
        Authority authority = new Authority("user", "ROLE_USER");

        String result = authority.getUsername();

        assertEquals("user", result);
    }

    @Test
    void setUsername() {
        Authority authority = new Authority();
        authority.setUsername("user");

        String result = authority.getUsername();

        assertEquals("user", result);
    }

    @Test
    void getAuthority() {
        Authority authority = new Authority("user", "ROLE_USER");

        String result = authority.getAuthority();

        assertEquals("ROLE_USER", result);
    }

    @Test
    void setAuthority() {
        Authority authority = new Authority();
        authority.setAuthority("ADMIN");

        String result = authority.getAuthority();

        assertEquals("ADMIN", result);
    }
}