package com.revature.services;

import com.revature.dtos.Principal;
import com.revature.security.TokenGenerator;
import com.revature.security.TokenValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.revature.exceptions.UnauthorizedException;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {

    private final TokenValidator mockTokenValidator = mock(TokenValidator.class);

//    @Mock
//    private TokenValidator mockTokenValidator;

    private final TokenGenerator mockTokenGenerator = mock(TokenGenerator.class);

    private TokenService tokenService = new TokenService(mockTokenGenerator, mockTokenValidator);

    @DisplayName("Token Generation")
    @Test
    public void generateToken(){
        Principal subjectTest = new Principal();
        subjectTest.setId(1);
        subjectTest.setEmail("test@example.com");
//        subjectTest.setAdmin(false);
//        subjectTest.setActive(true);

        String expectedToken = "test_token";
        when(mockTokenGenerator.createToken(subjectTest)).thenReturn(expectedToken);
        String actualToken = tokenService.generateToken(subjectTest);
        assertEquals(expectedToken, actualToken);
    }

    @DisplayName("Token Validation")
    @Test
    public void isTokenValid(){
        String validToken = "valid_token";
        Principal expectedPrincipal = new Principal();
        expectedPrincipal.setId(1);
        expectedPrincipal.setEmail("test@example.com");
        when(mockTokenValidator.parseToken(validToken)).thenReturn(Optional.of(expectedPrincipal));

        String invalidToken = "";
        when(mockTokenValidator.parseToken(invalidToken)).thenReturn(Optional.empty());

        assertTrue(tokenService.isTokenValid(validToken));
        assertFalse(tokenService.isTokenValid(invalidToken));

    }

    @DisplayName("Token Details Extraction")
    @Test
    public void extractTokenDetails(){
        String validToken = "valid_token";
        Principal expectedPrincipal = new Principal();
        expectedPrincipal.setId(1);
        expectedPrincipal.setEmail("test@example.com");


        when(mockTokenValidator.parseToken(validToken)).thenReturn(Optional.of(expectedPrincipal));

        String invalidToken = "";
        when(mockTokenValidator.parseToken(invalidToken)).thenReturn(Optional.empty());

        Principal actualPrincipal = tokenService.extractTokenDetails(validToken);

        assertEquals(expectedPrincipal, actualPrincipal);

        try {
            tokenService.extractTokenDetails(invalidToken);
            fail();
        } catch (UnauthorizedException e) {
            assertEquals("No authentication token found on request", e.getMessage());
        }
    }

    @DisplayName("Token Expiration")
    @Test
    public void getDefaultTokenExpiry(){
        int expectedExpiry = 100;
        when(mockTokenValidator.getDefaultTokenExpiry()).thenReturn(expectedExpiry);

        int actualExpiry = tokenService.getDefaultTokenExpiry();

        assertEquals(expectedExpiry, actualExpiry);
    }

//    @DisplayName("Principal Validation")
//    @Test
//    public void isPrincipalValid(){
//        Principal validPrincipal = new Principal();
//        validPrincipal.setId(1);
//        validPrincipal.setEmail("test@example.com");
//        assertTrue(tokenService.isPrincipalValid(validPrincipal));
//
//        Principal invalidPrincipal = new Principal();
//        invalidPrincipal.setId(2);
//        invalidPrincipal.setEmail("");
//        assertFalse(tokenService.isPrincipalValid(invalidPrincipal));
//
//        invalidPrincipal = null;
//        assertFalse(tokenService.isPrincipalValid(invalidPrincipal));
//    }

}
