package com.revature.services;

import com.revature.dtos.RegisterRequest;
import com.revature.dtos.UpdateUserRequest;
import com.revature.dtos.UserResponse;
import com.revature.exceptions.InvalidUserInputException;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.exceptions.ResourcePersistanceException;
import com.revature.controllers.models.User;
import com.revature.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Predicate;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByCredentials(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Transactional(readOnly = true)
    public void isEmailAvailable(String email){
        if(userRepository.checkEmail(email).isPresent())
            throw new InvalidUserInputException("Email: " + email + " is already registered please try again. Or Log in with email & password.");
    }

    @Transactional
    public UserResponse registerUser(RegisterRequest registerRequest) throws InvalidUserInputException, ResourcePersistanceException {
        User newUser = new User(registerRequest);
        isEmailAvailable(newUser.getEmail());
        return new UserResponse(userRepository.save(newUser));
    }

    @Transactional
    public void update(UpdateUserRequest updateUserRequest, User currentUser) throws InvalidUserInputException{
        User foundUser = userRepository.findById(currentUser.getId()).orElseThrow(ResourceNotFoundException::new);
        Predicate<String> notNullOrEmpty = (str) -> str != null && !str.trim().equals("");

        if(notNullOrEmpty.test(updateUserRequest.getFirstName()))
            foundUser.setFirstName(updateUserRequest.getFirstName());

        if(notNullOrEmpty.test(updateUserRequest.getLastName()))
            foundUser.setLastName(updateUserRequest.getLastName());

        if(notNullOrEmpty.test(updateUserRequest.getPassword()))
            foundUser.setPassword(updateUserRequest.getPassword());



        }

    @Transactional
    public void deactivate(User currentUser) throws InvalidUserInputException{
        User foundUser = userRepository.findById(currentUser.getId()).orElseThrow(ResourceNotFoundException::new);
        userRepository.deactivateUser(foundUser.getId());
    }

    @Transactional(readOnly = true)
    public UserResponse findById(int id){
        User user = userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        UserResponse userResponse = new UserResponse(user);
        return userResponse;
    }

    @Transactional(readOnly = true)
    public User findUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return user;
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}



