package com.revature.services;

import com.revature.dtos.RegisterRequest;
import com.revature.dtos.UpdateUserRequest;
import com.revature.exceptions.ResourceNotFoundException;
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
        user1 = new User(1, "Valid", "Valid", "Valid", "Valid", false, true,null);
        registerRequest = new RegisterRequest("Valid", "Valid", "Valid", "Valid");
        updateUserRequest = new UpdateUserRequest("Valid", "Valid", "Valid","Valid");
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

        //Use thenThrows to test for errors and exceptions thrown during method execution
        when(userRepository.save(any())).thenReturn(user1);
        userService.registerUser(registerRequest);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test will pass when deactivate user is successful given a valid user")
    public void testDeactivate_givenValidInput(){
        when(userRepository.findById(user1.getId())).thenReturn(Optional.ofNullable(user1));
        userService.deactivate(user1);
        verify(userRepository, times(1)).deactivateUser(user1.getId());
    }

}