package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.dtos.RegisterRequest;
import com.revature.dtos.UpdateUserRequest;
import com.revature.dtos.UserResponse;
import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000", "http://127.0.0.1:3000"},  allowCredentials = "true")
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
        System.out.println(registerRequest);
        return userService.registerUser(registerRequest);
    }

    @PutMapping
    public String update(@RequestBody UpdateUserRequest updateUserRequest, HttpSession session) {

        userService.update(updateUserRequest, (User) session.getAttribute("user"));
        return "The user account is successfully updated!";
    }

    @Authorized
    @GetMapping // For user to use
    public UserResponse getProfile(HttpSession session) {
        User user = (User) session.getAttribute("user");
        UserResponse userResponse = new UserResponse(userService.findUserById(user.getId())); //We get users id here to get the most recent updated user information from db
        return userResponse;
    }

    @PutMapping("/deactivate")
    public String deactivateAccount(HttpSession session) {
        User user = (User) session.getAttribute("user");
        userService.deactivate(user);
        return "The user account is successfully deactivated!";
    }

    @PutMapping("/deactivateUser")
    @Authorized(isAdmin = true)
    public String deactivateUser(User user) {
        userService.deactivate(user);
        return "The user account is successfully deactivated!";
    }
}
