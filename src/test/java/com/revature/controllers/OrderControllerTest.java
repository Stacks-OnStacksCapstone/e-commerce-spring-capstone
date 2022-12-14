package com.revature.controllers;


import com.revature.ECommerceApplication;
import com.revature.dtos.CreateOrderRequest;
import com.revature.dtos.OrderResponse;
import com.revature.dtos.Principal;
import com.revature.models.Order;
import com.revature.models.OrderDetail;
import com.revature.models.User;
import com.revature.repositories.OrderRepository;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ECommerceApplication.class)
public class OrderControllerTest {
    @MockBean(name="AuthService")
    private AuthService authService;

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    public String getToken() throws Exception {

        User user1 = userService.findUserById(1);
        Principal payload = new Principal(user1);
        String token = tokenService.generateToken(payload);
        return token;
    }
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testapiorderget() throws Exception {
        mockMvc.perform(get("/api/order")
                        .header("Authorization", getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                        .andDo(print())
                        .andExpect(status().isOk());

    }
    //One line not covered here!

    @Test
    public void testapiorderid() throws Exception {
        mockMvc.perform(get("/api/order/1")
                        .header("Authorization", getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk());

        }

    // Fake order id is not triggering line 51, why? It seems like the team handles
    // a fake idea before it even
    //reaches the if statement. (One line not covered here).
    @Test
    public void testfakeapiorderid() throws Exception {

        mockMvc.perform(get("/api/order/6")
                        .header("Authorization", getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

    //All lines covered here
     @Test
    public void testapiorderhistory() throws Exception {
        mockMvc.perform(get("/api/order/history")
                        .header("Authorization", getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk());
    }


    //Should return 200 but returns 400
    @Test
    public void testapiorderpost() throws Exception {
        mockMvc.perform(post("/api/order")
                        .header("Authorization", getToken())
                         .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                        "    \"paymentId\": \"5bc1bb79-6ef8-48e1-be83-8dfee8f981a7\",\n" +
                        "    \"shipmentAddress\": \"9999888877776666\"\n" +
                        "}")
                         .accept(MediaType.APPLICATION_JSON))
                         .andDo(print())
                         .andExpect(status().isOk());
    }
}
