package com.revature.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.OrderDetailController;
import com.revature.controllers.UserController;
import com.revature.dtos.OrderDetailResponse;
import com.revature.dtos.ProductReviewResponse;
import com.revature.models.User;
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
import java.util.List;

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

    @Test
    @DisplayName("Get all  - /api/orderdetail/order/{id}")
    public void getOrderDetailByOrderID() throws Exception {
        String jsonString = "{\"ordersId\":1,\"productId\":1,\"id\":1,\"quantity\": 1}";
        ObjectMapper objectMapper = new ObjectMapper();
        OrderDetailResponse odd = objectMapper.readValue(jsonString,OrderDetailResponse.class);
        List<OrderDetailResponse> oddList = new ArrayList<>();
        oddList.add(odd);
        User muser = new User(1, "fake@email.com", "password", "Fake", "Name", false, true, "fjidaop3898awe8f");
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
}
