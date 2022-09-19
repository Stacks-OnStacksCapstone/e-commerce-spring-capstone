package com.revature.services;

import com.revature.dtos.ResetPasswordRequest;
import com.revature.dtos.UpdateUserRequest;
import com.revature.exceptions.InvalidUserInputException;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public Optional<User> findByCredentials(String email, String password) {
        return userService.findByCredentials(email, password);
    }

    @Transactional
    public void resetPassword(UpdateUserRequest updateUserRequest) {
        User user = userService.findByEmail(updateUserRequest.getEmail()).orElseThrow(ResourceNotFoundException::new);
        if(updateUserRequest.getPassword() == null || updateUserRequest.getPassword().equals("")) {
            throw new InvalidUserInputException();
        }
        userService.update(updateUserRequest, user);
    }

    public User register(User user) {
        return userService.save(user);
    }
}
