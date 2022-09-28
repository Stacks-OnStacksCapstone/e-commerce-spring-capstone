package com.revature.dtos;

import com.revature.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class Principal {

    @NotBlank
    private int id;
    @NotBlank
    private String email;
    private boolean isAdmin;
    private boolean isActive;

    public Principal(User authUser) {
        this.id = authUser.getId();
        this.email = authUser.getEmail();
        this.isAdmin = authUser.isAdmin();
        this.isActive = authUser.isActive();
    }

    public Principal(int id, String email, boolean isAdmin, boolean isActive) {
        this.id = id;
        this.email = email;
        this.isAdmin = isAdmin;
        this.isActive = isActive;
    }

    public User extractUser() {
        return new User();
    }
}
