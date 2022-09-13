package com.revature.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUserRequest {

    private String firstName;
    private String lastName;
    private String password;


}
