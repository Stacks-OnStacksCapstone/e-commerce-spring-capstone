package com.revature.controllers;

import com.revature.dtos.*;
import com.revature.exceptions.UnauthorizedException;
import com.revature.models.User;
import com.revature.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000", "http://127.0.0.1:3000"},  allowCredentials = "true")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<UserResponse> getCurrentUser(HttpSession session) {
        // If the user is not logged in
        System.out.println(session.getAttribute("user"));
        if(session.getAttribute("user") == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new UserResponse((User)session.getAttribute("user")));
    }

    @GetMapping("/reset-password/{token}")
    public ResponseEntity<Void> verifyResetPasswordToken(@PathVariable String token) {
        authService.verifyResetPasswordToken(token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Optional<User> optional = authService.findByCredentials(loginRequest.getEmail(), loginRequest.getPassword());

        if(!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        if(!optional.get().isActive()) {
            throw new UnauthorizedException("Your account is deactivated and not able to log in!");
        }



        session.setAttribute("user", optional.get());

        return ResponseEntity.ok(new UserResponse(optional.get()));
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
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("user");

        return ResponseEntity.ok().build();
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest registerRequest) {
        User created = new User(registerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(authService.register(created)));
    }

}
