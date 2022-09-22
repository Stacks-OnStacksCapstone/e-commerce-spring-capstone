package com.revature.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor

//For testing
@AllArgsConstructor
//////////////////

public class UpdateUserRequest {

    private String firstName;
    private String lastName;
    private String email;
    @Pattern(message = "The password syntax is invalid! please try again.",regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private String password;


}
