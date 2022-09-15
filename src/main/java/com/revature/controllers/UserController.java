package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.dtos.RegisterRequest;
import com.revature.dtos.UpdateUserRequest;
import com.revature.dtos.UserResponse;
import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; //TODO: What is that?
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public UserResponse register(@RequestBody RegisterRequest registerRequest) {
        return userService.registerUser(registerRequest);
    }

    @PutMapping
    public String update(@RequestBody UpdateUserRequest updateUserRequest, HttpSession session) {

        userService.update(updateUserRequest, (User) session.getAttribute("user"));
        return "The user account is successfully updated!";
    }

    @GetMapping // For user to use
    public UserResponse getProfile(HttpSession session) {
        User user = (User) session.getAttribute("user");
        UserResponse userResponse = new UserResponse(user);
        return userResponse;
    }

    @PutMapping("/deactivate")
    public String deactivateAccount(@RequestBody HttpSession session) {

        userService.deactivate( (User) session.getAttribute("user"));
        return "The user account is successfully deactivated!";
    }
}

