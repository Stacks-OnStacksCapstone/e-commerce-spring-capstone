package com.revature.dtos;

import com.revature.models.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    private int userId;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isAdmin;
    private boolean isActive;

    public UserResponse(User user){

        this.userId = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.isActive = user.isActive();
        this.isAdmin = user.isAdmin();
    }


}
