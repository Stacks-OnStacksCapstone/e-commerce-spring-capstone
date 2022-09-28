package com.revature.services;

import com.revature.dtos.RegisterRequest;
import com.revature.dtos.UpdateUserRequest;
import com.revature.dtos.UserResponse;
import com.revature.exceptions.InvalidUserInputException;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.exceptions.ResourcePersistanceException;
import com.revature.models.User;
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

    @Transactional
    public Optional<User> findByCredentials(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Transactional
    public Optional<User> findByEmail(String email){
        return userRepository.checkEmail(email);
    }

    @Transactional
    public Optional<User> findByResetPasswordToken(String resetPasswordToken) {
        return userRepository.findByResetPasswordToken(resetPasswordToken);
    }

    @Transactional
    public void updateResetPasswordToken(String resetPasswordToken, String email) {
        User user = findByEmail(email).orElseThrow(ResourceNotFoundException::new);

        user.setResetPasswordToken(resetPasswordToken);
        userRepository.save(user);
    }

    @Transactional
    public void resetPassword(User user, String newPassword) {
        user.setPassword(newPassword);
        user.setResetPasswordToken(null);

        userRepository.save(user);
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public boolean isEmailAvailable(String email){
        if(userRepository.checkEmail(email).isPresent())
            throw new InvalidUserInputException("Email: " + email + " is already registered please try again. Or Log in with email & password.");
        return true;
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

    @Transactional
    public void deactivateUser(User user) throws InvalidUserInputException{
        User foundUser = userRepository.findById(user.getId()).orElseThrow(ResourceNotFoundException::new);
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
}



