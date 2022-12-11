package com.revature.controllers;


import com.revature.ECommerceApplication;
import com.revature.dtos.CreateOrderRequest;
import com.revature.dtos.OrderResponse;
import com.revature.models.Order;
import com.revature.models.User;
import com.revature.repositories.OrderRepository;
import com.revature.services.AuthService;
import com.revature.services.OrderService;
import com.revature.services.ProductService;
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
@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @MockBean(name="AuthService")
    private AuthService authService;

    @MockBean(name="OrderService")
    private OrderService orderService;


    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;


    private String auth = " eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoidGVzdHVzZXJAZ21haWwuY29tIiwiaXNzIjoiQ29uZ28iLCJpc0" +
            "FkbWluIjp0cnVlLCJpc0FjdGl2ZSI6dHJ1ZSw" +
            "iaWF0IjoxNjcwNjA5NzM2LCJleHAiOjE2NzA2OTYxMzZ9" +
            ".NAHFDXIqZr98I19uYyWh2UM8YrCxYIAWRx6sW_APH9Y";

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testapiorderget() throws Exception {
        mockMvc.perform(get("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                        .andDo(print())
                        .andExpect(status().isOk());



    }

//.header("Authorization",auth )
    @Test
    public void testapiorderid() throws Exception {
        int id = 1;
        when(orderService.findById(id)).thenReturn(new Order());
        mockMvc.perform(get("/api/order/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk());

        }


     @Test
    public void testapiorderhistory() throws Exception {
        List<OrderResponse> orderResponses = new ArrayList<>();
        when(orderService.findAllUserOrders(new User())).thenReturn(orderResponses);
        mockMvc.perform(get("/api/order/history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk());
    }


    //Should return 200 but returns 400
    @Test
    public void testapiorderpost() throws Exception {
        String token = "faketoken";
        when(authService.getUserByAuthToken(token)).thenReturn(new User());
        mockMvc.perform(post("/api/order")
                         .contentType(MediaType.APPLICATION_JSON)
                         .accept(MediaType.APPLICATION_JSON))
                         .andDo(print())
                         .andExpect(status().is4xxClientError());

    }



}
