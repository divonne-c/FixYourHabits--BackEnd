package com.fixyourhabitsbackend.security;

import com.fixyourhabitsbackend.payload.AuthenticationResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthenticationResponseTest {

    @Test
    void getJwt() {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse("jwt");

        String result = authenticationResponse.getJwt();

        assertEquals("jwt", result);
    }
}