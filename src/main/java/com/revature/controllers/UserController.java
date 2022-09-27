package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.dtos.RegisterRequest;
import com.revature.dtos.UpdateUserRequest;
import com.revature.dtos.UserResponse;
import com.revature.models.User;
import com.revature.services.TokenService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000", "http://127.0.0.1:3000", "http://e-commerce-congo-react-lb-919946656.us-east-1.elb.amazonaws.com"},  allowCredentials = "true")
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }


    @PostMapping
    public UserResponse register(@RequestBody RegisterRequest registerRequest) {
        System.out.println(registerRequest);
        return userService.registerUser(registerRequest);
    }

    @PutMapping
    public String update(@RequestBody UpdateUserRequest updateUserRequest, HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        int userId = tokenService.extractTokenDetails(token).getId();
        User currentUser = userService.findUserById(userId);

        userService.update(updateUserRequest, currentUser);
        return "The user account is successfully updated!";
    }

    @Authorized
    @GetMapping // For user to use
    public ResponseEntity<UserResponse> getProfile(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        int userId = tokenService.extractTokenDetails(token).getId();

        //We get users id here to get the most recent updated user information from db
        return ResponseEntity.ok(userService.findById(userId));
    }

    @Authorized
    @PutMapping("/deactivate")
    public String deactivateAccount(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        int userId = tokenService.extractTokenDetails(token).getId();
        User currentUser = userService.findUserById(userId);

        userService.deactivate(currentUser);
        return "The user account is successfully deactivated!";
    }

    @PutMapping("/deactivateUser")
    @Authorized(isAdmin = true)
    public String deactivateUser(User user) {
        userService.deactivate(user);
        return "The user account is successfully deactivated!";
    }
}
