package com.revature.controllers;


import com.revature.ECommerceApplication;
import com.revature.dtos.Principal;
import com.revature.exceptions.UnauthorizedException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import com.revature.services.AuthService;
import com.revature.services.TokenService;
import com.revature.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//Sets the base class needed to start the H2 and have other calls available
@SpringBootTest(classes = ECommerceApplication.class)
public class AuthControllerTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    public String getToken() throws Exception {
        /*User user1 = new User(1,"testuser@gmail.com", "password", "Testerson", "Usertown",
                true, true,null);*/
        User user1 = userService.findUserById(1);
        Principal payload = new Principal(user1);
        String token = tokenService.generateToken(payload);
        return token;
    }

    public String getResetPasswordToken() throws Exception {
        String resetPasswordToken = UUID.randomUUID().toString();
        userService.updateResetPasswordToken(resetPasswordToken, "testuser@gmail.com");
        System.out.println(resetPasswordToken);
        return resetPasswordToken;
    }

    @Test
    public void testPositiveLogin() throws Exception {

        mockMvc.perform(post("/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n" +
                    "    \"email\": \"testuser@gmail.com\",\n" +
                    "    \"password\": \"password\"\n" +
                    "}")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void testNegativeBadUserName() throws Exception {

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"email\": \"fakeuser@gmail.com\",\n" +
                                "    \"password\": \"password\"\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testNegativeBadPassword() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"email\": \"testuser@gmail.com\",\n" +
                                "    \"password\": \"Bassword\"\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(post("/auth/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testPositiveRegister() throws Exception {
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"email\": \"newuser1@gmail.com\",\n" +
                                "  \"password\": \"Password1*\",\n" +
                                "  \"firstName\": \"Jeffy\",\n" +
                                "  \"lastName\": \"Jefferson\"\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath(".userId").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath(".email").value("newuser1@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath(".firstName").value("Jeffy"))
                .andExpect(MockMvcResultMatchers.jsonPath(".lastName").value("Jefferson"))
                .andExpect(MockMvcResultMatchers.jsonPath(".active").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath(".admin").value(false));
    }

    @Test
    public void testGetCurrentUser() throws Exception {
        String token = getToken();
        mockMvc.perform(get("/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".userId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".email").value("testuser@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath(".firstName").value("Testerson"))
                .andExpect(MockMvcResultMatchers.jsonPath(".lastName").value("Usertown"))
                .andExpect(MockMvcResultMatchers.jsonPath(".active").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath(".admin").value(true));
    }

    @Test
    public void testForgotPassword() throws Exception {
        String token = getToken();
        mockMvc.perform(put("/auth/forgot-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\": \"testuser@gmail.com\"\n" +
                        "}")
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testResetPasswordGet() throws Exception {
        String resetPasswordToken = getResetPasswordToken();
        mockMvc.perform(get("/auth/reset-password/{token}", resetPasswordToken)
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testResetPasswordPut() throws Exception {
        String resetPasswordToken = getResetPasswordToken();
        mockMvc.perform(put("/auth/reset-password/{token}", resetPasswordToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"email\": \"testuser@gmail.com\"\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
