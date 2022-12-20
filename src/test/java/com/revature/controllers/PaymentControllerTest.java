package com.revature.controllers;

import com.revature.ECommerceApplication;
import com.revature.dtos.Principal;
import com.revature.models.User;
import com.revature.services.TokenService;
import com.revature.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ECommerceApplication.class)
public class PaymentControllerTest {
    @Autowired
    PaymentController paymentController;
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

    public String getToken(int userId) throws Exception {
        User user1 = userService.findUserById(userId);
        Principal payload = new Principal(user1);
        String token = tokenService.generateToken(payload);
        return token;
    }

    //200postman 401 MVC(Must be logged in as payment owner to delete payment.)

    @Test
    public void deletepaymentNegative() throws Exception {
        mockMvc.perform(delete("/api/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",getToken(2))
                        .param("paymentId", "5bc1bb79-6ef8-48e1-be83-8dfee8f981a7"))
                
                .andExpect(content().string("Must be logged in as payment owner to delete payment."))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void deletepaymentCorrectUser() throws Exception {
        mockMvc.perform(delete("/api/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",getToken(1))
                        .param("paymentId", "3"))
                
                .andExpect(status().isOk());

    }

    @Test
    public void deletepaymentNoAuth() throws Exception {
        mockMvc.perform(delete("/api/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("paymentId", "3"))
                
                .andExpect(status().isUnauthorized());

    }

    @Test
    public void deletepaymentNullId() throws Exception {
        mockMvc.perform(delete("/api/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",getToken(3))
                        .param("paymentId", " " ))
                
                .andExpect(status().isNotFound());
    }

    @Test
    public void deletepaymentUnauthorizedException() throws Exception {
        mockMvc.perform(delete("/api/payment")
                        .header("Authorization", getToken(3)))
                
                .andExpect(status().isBadRequest());

    }

    @Test
    public void deletepaymentLargeNumber() throws Exception {
        mockMvc.perform(delete("/api/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",getToken(3))
                        .param("paymentId", "1000000" ))
                
                .andExpect(status().isNotFound());

    }

    @Test
    public void deletepaymentNoParam() throws Exception {
        mockMvc.perform(delete("/api/payment")
                        .contentType(MediaType.APPLICATION_JSON))
                
                .andExpect(status().isBadRequest());

    }

    @Test
    public void getpayment() throws Exception {
        mockMvc.perform(get("/api/payment").header("Authorization",getToken(1))
                        .contentType(MediaType.APPLICATION_JSON))
                
                .andExpect(status().isOk());
    }

    @Test
    public void postPayment() throws Exception {
        mockMvc.perform(post("/api/payment").header("Authorization",getToken(2))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"ccv\": \"string\",\n" +
                                "  \"expDate\": \"2022-12-05\",\n" +
                                "  \"cardNumber\": \"string\"}"))
                
                .andExpect(status().isOk());

    }
    @Test
    public void putPayment() throws Exception {
        mockMvc.perform(put("/api/payment").header("Authorization",getToken(1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"paymentId\": \"5bc1bb79-6ef8-48e1-be83-8dfee8f981a7\",\n" +
                                "  \"cardType\": \"str222ing\",\n" +
                                "  \"expDate\": \"2022-12-05\",\n" +
                                "  \"cardNumber\": \"string\"}"))
                
                .andExpect(status().isOk());
    }
}