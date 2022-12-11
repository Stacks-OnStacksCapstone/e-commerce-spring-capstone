package com.revature.controllers;
import com.revature.ECommerceApplication;
import com.revature.dtos.CreateOrderRequest;
import com.revature.dtos.OrderDetailRequest;
import com.revature.dtos.OrderDetailResponse;
import com.revature.dtos.OrderResponse;
import com.revature.models.Order;
import com.revature.models.OrderDetail;
import com.revature.models.User;
import com.revature.repositories.OrderRepository;
import com.revature.services.AuthService;
import com.revature.services.OrderDetailService;
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
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = ECommerceApplication.class)
 public class OrderDetailControllerTest {

    @MockBean(name="AuthService")
    private AuthService authService;

    @MockBean(name="OrderService")
    private OrderService orderService;

    @MockBean(name="OrderDetailService")
    private OrderDetailService orderDetailService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    private String auth = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoidGVzdHVzZXJAZ21haWwuY29tIiwiaXNzIjoiQ29uZ28iLCJpc0FkbWluIjp0cnV" +
            "lLCJpc0FjdGl2ZSI6dHJ1ZSwiaWF0IjoxNj" +
            "cwNzk0NDY0LCJleHAiOjE2NzA4ODA4NjR9.TxXkPNCqblPG" +
            "CxHdguVB1114qRrt5CPQkm8s0qJu4Hc";


    //Getting a 404 response should be 200.

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
    public void testapiorderdetailgetbyid() throws Exception {
        //when(orderDetailService.createOrderDetail(new OrderDetailRequest())).thenReturn(new OrderDetailResponse());
        mockMvc.perform(post("/api/orderdetail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",auth)
                        .content(" \"productId\": \"1\",\n" +
                                "  \"orderId\": \"1\",\n" +
                                "  \"quantity\": \"0\""))
                        .andDo(print())
                        .andExpect(status().isOk());

    }


    //Get Auth from postman!
    @Test
    public void testdeleteapiorderdetailbyid() throws Exception {
        int id = 1;
        when( orderDetailService.delete(id)).thenReturn(true);
        mockMvc.perform(delete("/api/orderdetail/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",auth)
                        .content(""))
                .andDo(print())
                .andExpect(status().isOk());
            }


    //Get a 404 response should be 200
    @Test
    public void testgetapiorderdetailbyid() throws Exception {
        int id = 1;
        Optional<OrderDetail> optional = orderDetailService.findById(id);
        when(orderDetailService.findById(id)).thenReturn(optional);
        mockMvc.perform(get("/api/orderdetail/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
            }


    //Get a 404 response should be 200
    @Test
    public void testgetapiorderdetailbyorder() throws Exception {
        mockMvc.perform(get("/api/orderdetail/order/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
