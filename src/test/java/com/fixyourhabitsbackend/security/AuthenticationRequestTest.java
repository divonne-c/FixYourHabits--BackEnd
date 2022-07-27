package com.fixyourhabitsbackend.security;

import com.fixyourhabitsbackend.security.AuthenticationRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthenticationRequestTest {

    @Test
    void getUsername() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("user", "password");

        String result = authenticationRequest.getUsername();

        assertEquals("user", result);
    }

    @Test
    void setUsername() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUsername("user");

        String result = authenticationRequest.getUsername();

        assertEquals("user", result);
    }

    @Test
    void getPassword() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("user", "password");

        String result = authenticationRequest.getPassword();

        assertEquals("password", result);
    }

    @Test
    void setPassword() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setPassword("password");

        String result = authenticationRequest.getPassword();

        assertEquals("password", result);
    }
}