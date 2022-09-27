package com.revature.dtos;

import com.revature.models.User;

import javax.validation.constraints.NotBlank;

public class Principal {

    @NotBlank
    private int id;
    @NotBlank
    private String email;
    private boolean isAdmin;

    public Principal() {}
    public Principal(User authUser) {
        this.id = authUser.getId();
        this.email = authUser.getEmail();
        this.isAdmin = authUser.isAdmin();
    }
    public Principal(int id, String email, boolean isAdmin) {
        this.id = id;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public User extractUser() {
        return new User();
    }

    @Override
    public String toString() {
        return "Principal{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
