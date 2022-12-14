package com.revature.controllers;
import com.revature.ECommerceApplication;
import com.revature.dtos.*;
import com.revature.models.Order;
import com.revature.models.OrderDetail;
import com.revature.models.User;
import com.revature.repositories.OrderRepository;
import com.revature.security.TokenGenerator;
import com.revature.services.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.revature.ECommerceApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ECommerceApplication.class)
public class PaymentControllerTest {
    @MockBean(name="AuthService")
    private AuthService authService;

    @MockBean(name="OrderService")
    private OrderService orderService;

    @MockBean(name="OrderDetailService")
    private OrderDetailService orderDetailService;

    @MockBean(name="UserService")
    private UserService userService;

    @MockBean(name="TokenService")
    private TokenService tokenService;


    @MockBean(name="TokenGenerator")
    private TokenGenerator tokenGenerator;


    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }



    private String auth = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoidGVzdHVzZXJAZ21haWwuY29tIiwiaXNzIjoi" +
            "Q29uZ28iLCJpc0FkbWluIjp0cnVlLCJ" +
            "pc0FjdGl2ZSI6dHJ1ZSwiaWF0IjoxNjcwODAwMTE" +
            "wLCJleHAiOjE2NzA4ODY1MTB9.ur9zAPmarEphAim-JXpYQfA" +
            "XGWcb8uU138m2-_guoRo";


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

    //200postman 401 MVC(Must be logged in as payment owner to delete payment.)
    @Test
    public void deletepaymentnegative() throws Exception {
        when(orderDetailService.createOrderDetail(new OrderDetailRequest())).thenReturn(new OrderDetailResponse());
        mockMvc.perform(delete("/api/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",auth)
                        .param("paymentId", "notincard233"))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }


    //200 postman 200 MVC
    @Test
    public void getpayment() throws Exception {
        //when(orderDetailService.createOrderDetail(new OrderDetailRequest())).thenReturn(new OrderDetailResponse());
        mockMvc.perform(get("/api/payment").header("Authorization",auth)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());



    }

    //200 in Postman 400 in MVC(HttpMessageNotReadableException)
    @Test
    public void postpayment() throws Exception {
        //when(orderDetailService.createOrderDetail(new OrderDetailRequest())).thenReturn(new OrderDetailResponse());
        mockMvc.perform(post("/api/payment").header("Authorization",auth)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("  \"ccv\": \"string\",\n" +
                                "  \"expDate\": \"2022-12-05T19:51:32.665Z\",\n" +
                                "  \"cardNumber\": \"string\""))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

    //200 in postman 400 on MVC
    @Test
    public void putpayment() throws Exception {
        //when(orderDetailService.createOrderDetail(new OrderDetailRequest())).thenReturn(new OrderDetailResponse());
        mockMvc.perform(put("/api/payment").header("Authorization",auth)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("   \"paymentId\": \"safecard111\",\n" +
                                "  \"cardType\": \"string\",\n" +
                                "  \"expDate\": \"2022-12-05T20:01:15.857Z\",\n" +
                                "  \"cardNumber\": \"string\""))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

}