package com.revature.models;

import com.revature.dtos.RegisterRequest;
import com.revature.dtos.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isAdmin;
    private boolean isActive;
    private String resetPasswordToken;

    public User(RegisterRequest registerRequest) {
        this.email = registerRequest.getEmail();
        this.firstName = registerRequest.getFirstName();
        this.lastName = registerRequest.getLastName();
        this.password = registerRequest.getPassword();
        this.isAdmin = false;
        this.isActive = true;
    }

    
    
    public User(UserResponse userResponse) {
        this.email = userResponse.getEmail();
        this.firstName = userResponse.getFirstName();
        this.lastName = userResponse.getLastName();
        this.isActive = userResponse.isActive();
    }

}
