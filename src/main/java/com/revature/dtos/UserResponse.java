package com.revature.dtos;

import com.revature.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isAdmin;
    private boolean isActive;

    public UserResponse(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.isAdmin = user.isAdmin();
        this.isActive = user.isActive();
    }
}
