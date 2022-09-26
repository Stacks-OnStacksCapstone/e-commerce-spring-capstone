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

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.mockito.BDDMockito.given;


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
    public void setUp() {
        user1 = new User(1, "Valid", "Valid", "Valid", "Valid", false, true, null);
        registerRequest = new RegisterRequest("Valid", "Valid", "Valid", "Valid");
        updateUserRequest = new UpdateUserRequest("Valid", "Valid", "Valid", "Valid");

    }

    @AfterEach
    public void tearDown() {
        user1 = null;
        registerRequest = null;
        updateUserRequest = null;
    }

    //PASSES!
    @Test
    @DisplayName("Test should pass when registering user with valid input data")
    public void testRegisterUser_givenValidInput() {

        //Use thenThrows to test for errors and exceptions thrown during method execution
        when(userRepository.save(any())).thenReturn(user1);
        userService.registerUser(registerRequest);
        verify(userRepository, times(1)).save(any());
    }



    // JUnit test for getEmployeeById method
    @DisplayName("JUnit test for findUserById method")
    @Test
    public void givenUserId_whenGetUserById_thenReturnUserObject() {
        // given
        given(userRepository.findById(1)).willReturn(Optional.of(user1));

        // when
        User savedUSer = userService.findUserById(user1.getId());

        // then
        assertThat(savedUSer).isNotNull();

    }

    // JUnit test for update method
    @DisplayName("JUnit test for update method")
    @Test
    public void givenUserObject_whenUpdateUser_thenReturnUpdatedUser() {
        // given - precondition or setup
        given(userRepository.findById(user1.getId())).willReturn(Optional.ofNullable(user1));


        // when -  action or the behaviour that we are going test
        userService.update(updateUserRequest, user1);

        // then - verify the output
        assertThat(user1.getFirstName()).isEqualTo(updateUserRequest.getFirstName());
        assertThat(user1.getLastName()).isEqualTo(updateUserRequest.getLastName());
        assertThat(user1.getPassword()).isEqualTo(updateUserRequest.getPassword());

    }

    @DisplayName("JUnit test for deactivate method")
    @Test
    public void givenUserObject_whenDeactivateUser_thenReturnDeactivatedUser() {
        // given - precondition or setup
        given(userRepository.findById(1)).willReturn(Optional.ofNullable(user1));

        // when -  action or the behaviour that we are going test
        userService.deactivate(user1);

        // then - verify the output
        verify(userRepository, times(1)).deactivateUser(user1.getId());

    }

    @DisplayName("JUnit test for save method")
    @Test
    public void givenUserObject_whenSaveUser_thenReturnUserObject() {
        // given - precondition or setup
        given(userRepository.save(user1)).willReturn(user1);

        System.out.println(userRepository);
        System.out.println(userService);

        // when -  action or the behaviour that we are going test
        User savedUser = userService.save(user1);

        System.out.println(savedUser);
        // then - verify the output
        assertThat(savedUser).isNotNull();
    }

    // JUnit test for getEmployeeById method
    @DisplayName("JUnit test for findByCredentials method")
    @Test
    public void givenUserEmailAndPassword_whenGetUserEmailAndPassword_thenReturnUserObject() {
        // given
        given(userRepository.findByEmailAndPassword(user1.getEmail(), user1.getPassword())).willReturn(Optional.of(user1));

        // when
        Optional<User> savedUser = userService.findByCredentials("Valid", "Valid");

        // then
        assertThat(savedUser).isNotNull();

    }

}