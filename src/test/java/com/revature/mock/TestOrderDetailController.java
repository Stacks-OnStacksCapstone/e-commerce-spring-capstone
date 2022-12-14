package com.revature.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.OrderDetailController;
import com.revature.dtos.CreateOrderRequest;
import com.revature.dtos.OrderDetailRequest;
import com.revature.dtos.OrderDetailResponse;
import com.revature.models.*;
import com.revature.services.AuthService;
import com.revature.services.OrderDetailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers= OrderDetailController.class)
public class TestOrderDetailController {

    @MockBean
    private OrderDetailService orderDetailService;
    @MockBean
    private AuthService authService;
    @Autowired
    private MockMvc mockMvc;
    User muser = new User(1, "fake@email.com", "password", "Fake", "Name", false, true, "fjidaop3898awe8f");
    CreateOrderRequest orderRequest = new CreateOrderRequest("1", "fakeAddress");
    Payment payment = new Payment("1", "1111222233334444", "000", new Date(2022, 12, 23), muser);
    Order order = new Order(orderRequest, muser, payment);
    OrderDetailRequest odrequest = new OrderDetailRequest(1, 1, 1);
    Product product = new Product(1, 1, 20.00, "cool product", "image", "name", true);
    String jsonString = "{\"ordersId\":1,\"productId\":1,\"id\":1,\"quantity\": 1}";
    OrderDetail od = new OrderDetail(odrequest, order, product);
    @Test
    @DisplayName("Get order detail by id  - /api/orderdetail/order/{id}")
    public void getOrderDetailByOrderId() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        OrderDetailResponse odresponse = objectMapper.readValue(jsonString,OrderDetailResponse.class);
        List<OrderDetailResponse> oddList = new ArrayList<>();
        oddList.add(odresponse);

        when(orderDetailService.findById(1)).thenReturn(Optional.of(new OrderDetail(odrequest, order, product)));
        when(authService.getUserByAuthToken(anyString())).thenReturn(muser);
        when(orderDetailService.findAllOrderDetailsByOrder(1)).thenReturn(oddList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/orderdetail/order/1")
                        .header("Authorization",""))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Get order details by id - /api/orderdetail/{id}")
    public void getOrderDetailsById() throws Exception {

        when(orderDetailService.findById(1)).thenReturn(Optional.of(od));
        when(authService.getUserByAuthToken(anyString())).thenReturn(muser);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/orderdetail/1")
                        .header("Authorization",""))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();
    }


}
