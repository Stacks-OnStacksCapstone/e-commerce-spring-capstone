package com.revature.services;

import com.revature.dtos.ResetPasswordRequest;
import com.revature.dtos.UpdateUserRequest;
import com.revature.exceptions.InvalidUserInputException;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.callback.ConfirmationCallback;
import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;
    private final SendEmailService sendEmailService;

    public AuthService(UserService userService, SendEmailService sendEmailService) {
        this.userService = userService;
        this.sendEmailService = sendEmailService;
    }

    public Optional<User> findByCredentials(String email, String password) {
        return userService.findByCredentials(email, password);
    }

    @Transactional
    public void resetPassword(UpdateUserRequest updateUserRequest) {
        User user = userService.findByEmail(updateUserRequest.getEmail()).orElseThrow(ResourceNotFoundException::new);

        String randomPassword = ":)";
        String to = user.getEmail();
        String subject = "Reset your Congo Password";
        String text = "Your password has been changed! \n New Password: " + randomPassword;

        sendEmailService.sendEmail(to,subject,text);



//        if(updateUserRequest.getPassword() == null || updateUserRequest.getPassword().equals("")) {
//            throw new InvalidUserInputException();
//        }
//        userService.update(updateUserRequest, user);
    }
    public User register(User user) {
        return userService.save(user);
    }
}
