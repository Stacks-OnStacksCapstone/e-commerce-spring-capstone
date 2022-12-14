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

public class UserControllerTest {

    @MockBean(name = "AuthService")
    private AuthService authService;

    @Autowired
    @InjectMocks
    private UserService userService;

    @Autowired
    @InjectMocks
    private TokenService tokenService;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    public String getToken() throws Exception {
        User user1 = userService.findUserById(1);
        Principal payload = new Principal(user1);
        String token = tokenService.generateToken(payload);
        return token;
    }

    @Test
    public void testUserRegister() throws Exception {
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"email\": \"newuser1@gmail.com\",\n" +
                                "  \"password\": \"Password1*\",\n" +
                                "  \"firstName\": \"Jeffy\",\n" +
                                "  \"lastName\": \"Jefferson\"\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".userId").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath(".email").value("newuser1@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath(".firstName").value("Jeffy"))
                .andExpect(MockMvcResultMatchers.jsonPath(".lastName").value("Jefferson"))
                .andExpect(MockMvcResultMatchers.jsonPath(".active").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath(".admin").value(false));
    }

    @Test
    public void testUserUpdate() throws Exception {
        String token = getToken();
        mockMvc.perform(put("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"email\": \"testuser@gmail.com\",\n" +
                                "  \"password\": \"password\",\n" +
                                "  \"firstName\": \"Testerson\",\n" +
                                "  \"lastName\": \"Usertown\"\n" +
                                "}")
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("The user account is successfully updated!"));
    }

    @Test
    public void testUserGetProfile() throws Exception {
        String token = getToken();
        mockMvc.perform(get("/user")
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
    public void testDeactivate() throws Exception {
        String token = getToken();
        mockMvc.perform(put("/user/deactivate")
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("The user account is successfully deactivated!"));

    }

    @Test
    public void testDeactivateUser() throws Exception {
        String token = getToken();
        mockMvc.perform(put("/user/deactivateUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"userId\": 1,\n" +
                        "  \"email\": \"testuser@gmail.com\",\n" +
                        "  \"password\": \"password\",\n" +
                        "  \"firstName\": \"Testerson\",\n" +
                        "  \"lastName\": \"Usertown\"\n" +
                        "  \"resetPasswordToken\": \"string\"\n" +
                        "  \"active\": true,\n" +
                        "  \"admin\": true\n" +
                        "}")
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("The user account is successfully deactivated!"));
    }


}
