package com.revature.services;

import com.revature.dtos.RegisterRequest;
import com.revature.dtos.UpdateUserRequest;
import com.revature.dtos.UserResponse;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private UserService userService;
    private User user1;
    private RegisterRequest registerRequest;
    private UpdateUserRequest updateUserRequest;

    @BeforeEach
    public void setUp(){
        user1 = new User(1, "Valid", "Valid", "Valid", "Valid", false, true);
        registerRequest = new RegisterRequest("Valid", "Valid", "Valid", "Valid");
        updateUserRequest = new UpdateUserRequest("Valid", "Valid", "Valid");
    }

    @AfterEach
    public void tearDown(){
        user1 = null;
        registerRequest = null;
        updateUserRequest = null;
    }

    //PASSES!
    @Test
    @DisplayName("Test should pass when registering user with valid input data")
    public void testRegisterUser_givenValidInput(){

        when(userRepository.save(any())).thenReturn(user1);
        userService.registerUser(registerRequest);
        verify(userRepository, times(1)).save(any());
    }

//    @Test
//    @DisplayName("Test should pass when updating a user given valid input")
//    public void testUpdateUser_givenValidInput(){
//
//        when(userRepository.findById().thenReturn(user1);
//        userService.update(updateUserRequest, user1);
//        verify(userRepository, times(1)).save(any());
//    }
}