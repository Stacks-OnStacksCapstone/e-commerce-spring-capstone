package com.revature.services;


import com.revature.dtos.UpdateUserRequest;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private SendEmailService sendEmailService;

    @Autowired
    @InjectMocks
    private AuthService authService;

    @Autowired
    @Mock
    private UserService userService;

    private UpdateUserRequest updateUserRequest;



    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByCredentials() {
    }

    @Test
    void forgotPassword() {

        UpdateUserRequest user = new UpdateUserRequest();
        user.setEmail("jj@mail.com");

        //when(userRepository.save(any())).thenReturn(new User());
        doNothing().when(userService).updateResetPasswordToken(isA(String.class),isA(String.class));

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                if(!args[0].equals(user.getEmail()) && args[2].toString().length() == 0 ){
                    fail();
                }
                return null;
            }
        }).when(sendEmailService).sendEmail(isA(String.class),isA(String.class),isA(String.class));

        authService.forgotPassword(user);

    }

    @Test
    void register() {
    }
}