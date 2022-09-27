package com.revature.services;

import com.revature.dtos.Principal;
import com.revature.dtos.UpdateUserRequest;
import com.revature.dtos.UserResponse;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final UserService userService;
    private final SendEmailService sendEmailService;
    private final TokenService tokenService;

    public AuthService(UserService userService, SendEmailService sendEmailService, TokenService tokenService) {
        this.userService = userService;
        this.sendEmailService = sendEmailService;
        this.tokenService = tokenService;
    }

    @Transactional
    public Optional<User> findByCredentials(String email, String password) {
        return userService.findByCredentials(email, password);
    }

    public void verifyResetPasswordToken(String token) {
        userService.findByResetPasswordToken(token).orElseThrow(ResourceNotFoundException::new);
    }

    public void resetPassword(String token, String newPassword) {
        User user = userService.findByResetPasswordToken(token).orElseThrow(ResourceNotFoundException::new);
        userService.resetPassword(user,newPassword);
    }

    public User getUserByAuthToken(String token) {
        int userId = tokenService.extractTokenDetails(token).getId();
        User user = userService.findUserById(userId);
        return user;
    }

    public String generateAuthToken(User user) {
        Principal payload = new Principal(user);
        String token = tokenService.generateToken(payload);
        return token;
    }

    @Transactional
    public void forgotPassword(UpdateUserRequest updateUserRequest) {

        String token = UUID.randomUUID().toString();

        userService.updateResetPasswordToken(token, updateUserRequest.getEmail());
        String resetPasswordLink = "http://localhost:3000/reset-password/" + token;

        String to = updateUserRequest.getEmail();
        String subject = "Reset your Congo Password";
        String text = "Click the link below to change your password\nIgnore this message if you didn't request your password to be changed\n" + resetPasswordLink;

        sendEmailService.sendEmail(to,subject,text);
    }
    public User register(User user) {
        return userService.save(user);
    }
}
