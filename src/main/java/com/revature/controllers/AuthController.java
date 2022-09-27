package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.dtos.*;
import com.revature.exceptions.UnauthorizedException;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.TokenService;
import com.revature.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000", "http://127.0.0.1:3000", "http://e-commerce-congo-react-lb-919946656.us-east-1.elb.amazonaws.com"},  allowCredentials = "true", exposedHeaders = "Authorization")
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;
    private final UserService userService;

    public AuthController(AuthService authService, TokenService tokenService, UserService userService) {
        this.authService = authService;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserResponse> getCurrentUser(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        int userId = tokenService.extractTokenDetails(token).getId();
        return ResponseEntity.ok(userService.findById(userId));
    }

    @GetMapping("/reset-password/{token}")
    public ResponseEntity<Void> verifyResetPasswordToken(@PathVariable String token) {
        authService.verifyResetPasswordToken(token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Principal> login(@RequestBody LoginRequest loginRequest, HttpServletResponse resp) {
        User authUser = authService.findByCredentials(loginRequest.getEmail(), loginRequest.getPassword()).orElseThrow(UnauthorizedException::new);
        if (!authUser.isActive()) throw new UnauthorizedException("User's account is currently inactive, Please login with another account");
        Principal payload = new Principal(authUser);
        String token = tokenService.generateToken(payload);
        resp.setHeader("Authorization", token);
        return ResponseEntity.ok(payload);
    }

    @PutMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@RequestBody UpdateUserRequest updateUserRequest){
        authService.forgotPassword(updateUserRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reset-password/{token}")
    public ResponseEntity<Void> resetPassword(@PathVariable String token, @RequestBody UpdateUserRequest updateUserRequest) {
        authService.resetPassword(token,updateUserRequest.getPassword());
        return ResponseEntity.ok().build();
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse resp) {
        resp.setHeader("Authorization","");
        return ResponseEntity.ok().build();
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest registerRequest) {
        User created = new User(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(authService.register(created)));
    }

}
