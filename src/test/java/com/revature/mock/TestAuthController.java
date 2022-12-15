package com.revature.mock;

import com.google.gson.Gson;
import com.revature.controllers.AuthController;
import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.dtos.UpdateUserRequest;
import com.revature.models.User;
import com.revature.services.AuthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
public class TestAuthController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Test
    @DisplayName("Test that the AuthController returns a user when given valid credentials")
    public void testGetCurrentUser() throws Exception {
        // Set up the mock service to return a sample user
        User user = new User();
        user.setId(1);
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("test@gmail.com");
        user.setPassword("password");
        user.setAdmin(false);
        user.setActive(true);
        when(authService.getUserByAuthToken("test-token")).thenReturn(user);

        // Perform the GET request to the endpoint, passing the "Authorization" header
        mockMvc.perform(MockMvcRequestBuilders.get("/auth").header("Authorization", "test-token"))
                .andExpect(status().isOk())
                // Check that the response body contains the expected values
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.lastName").value("User"))
                .andExpect(jsonPath("$.email").value("test@gmail.com"))
                .andExpect(jsonPath("$.active").value(true))
                .andExpect(jsonPath("$.admin").value(false))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Test the reset password get endpoint")
    public void testVerifyResetPasswordToken() throws Exception {
        // Perform the GET request to the endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/reset-password/test-token"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        // Verify that the auth service was called with the correct token
        verify(authService).verifyResetPasswordToken("test-token");
    }

    @Test
    @DisplayName("Test the login endpoint")
    public void testLogin() throws Exception {
        // Set up the mock service to return a sample user when findByCredentials() is called
        User user = new User();
        user.setId(1);
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("test@gmail.com");
        user.setPassword("password");
        user.setAdmin(false);
        user.setActive(true);
        when(authService.findByCredentials("test@gmail.com", "password")).thenReturn(Optional.of(user));
        when(authService.generateAuthToken(user)).thenReturn("test-token");

        // Set up the login request data
        LoginRequest loginRequest = new LoginRequest("test@gmail.com", "password");

        // Perform the POST request to the /login endpoint, passing the login request data
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(header().string("Authorization", "test-token"))
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.email").value("test@gmail.com"))
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.lastName").value("User"))
                .andExpect(jsonPath("$.active").value(true))
                .andExpect(jsonPath("$.admin").value(false))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Test the forgot password endpoint")
    public void testForgotPassword() throws Exception {
        // Set up the update user request data
        UpdateUserRequest updateUserRequest = new UpdateUserRequest("Test", "User", "test@gmail.com", "password");

        // Perform the PUT request to the /forgot-password endpoint, passing the update user request data
        mockMvc.perform(MockMvcRequestBuilders.put("/auth/forgot-password").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(updateUserRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        // Verify that the mock service's forgotPassword() method was called with the correct update user request data
        verify(authService).forgotPassword(updateUserRequest);
    }

    @Test
    @DisplayName("Test the reset password put endpoint")
    public void testResetPassword() throws Exception {
        // Set up the update user request data
        UpdateUserRequest updateUserRequest = new UpdateUserRequest("Test", "User", "test@gmail.com", "newpassword");

        // Perform the PUT request to the /reset-password/{token} endpoint, passing the token value and update user request data
        mockMvc.perform(MockMvcRequestBuilders.put("/auth/reset-password/abc123").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(updateUserRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        // Verify that the mock service's resetPassword() method was called with the correct token value and password
        verify(authService).resetPassword("abc123", "newpassword");
    }

    @Test
    @DisplayName("Test the logout endpoint")
    public void testLogout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/logout"))
                .andExpect(status().isOk())
                .andExpect(header().string("Authorization", ""))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Test the register endpoint")
    public void testRegister() throws Exception {
        // Set up the register request data
        RegisterRequest registerRequest = new RegisterRequest("test@gmail.com","password","Test","User");

        User user = new User(registerRequest);
        when(authService.register(user)).thenReturn(user);

        // Perform the POST request to the /register endpoint, passing the register request data
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(registerRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value(0))
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.lastName").value("User"))
                .andExpect(jsonPath("$.admin").value(false))
                .andExpect(jsonPath("$.active").value(true))
                .andExpect(jsonPath("$.email").value("test@gmail.com"))
                .andDo(print())
                .andReturn();
    }
}

