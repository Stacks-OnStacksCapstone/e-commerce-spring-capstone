package com.revature.controllers;


import com.revature.ECommerceApplication;
import com.revature.models.User;
import com.revature.services.AuthService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//Sets the base class needed to start the H2 and have other calls available
@SpringBootTest(classes = ECommerceApplication.class)
public class AuthControllerTest {

    @MockBean(name = "AuthService")
    private AuthService authService;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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
                                "  \"email\": \"newuser1\",\n" +
                                "  \"password\": \"pw\",\n" +
                                "  \"firstName\": \"Jeffy1*\",\n" +
                                "  \"lastName\": \"Jefferson1*\"\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
