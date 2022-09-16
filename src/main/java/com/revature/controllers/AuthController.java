package com.revature.controllers;

import com.revature.dtos.*;
import com.revature.models.User;
import com.revature.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<User> getCurrentUser(HttpSession session) {
        // If the user is not logged in
        System.out.println(session.getAttribute("user"));
        if(session.getAttribute("user") == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok((User)session.getAttribute("user"));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Optional<User> optional = authService.findByCredentials(loginRequest.getEmail(), loginRequest.getPassword());

        if(!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        session.setAttribute("user", optional.get());

        return ResponseEntity.ok(optional.get());
    }

    @PutMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody UpdateUserRequest updateUserRequest){
        if(updateUserRequest.getPassword() == null || updateUserRequest.getPassword().equals("")) {
            return ResponseEntity.badRequest().build();
        }
        authService.resetPassword(updateUserRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("user");

        return ResponseEntity.ok().build();
    }
}
