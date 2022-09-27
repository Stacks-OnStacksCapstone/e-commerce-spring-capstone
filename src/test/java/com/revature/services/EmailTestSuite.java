package com.revature.services;



import com.revature.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmailTestSuite {
    private static UserService userService;

    private static UserRepository userRepository;

    @BeforeAll
    static void init(){
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void test_isEmailAvailable(){
        when(userRepository.checkEmail(anyString())).thenReturn(Optional.empty());
        boolean result = userService.isEmailAvailable("test@mail.com");

        assertTrue(result);
    }
}
