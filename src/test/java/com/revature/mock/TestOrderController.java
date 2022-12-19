package com.revature.mock;

import com.google.gson.Gson;
import com.revature.controllers.OrderController;
import com.revature.dtos.CreateOrderRequest;
import com.revature.dtos.OrderResponse;
import com.revature.models.Order;
import com.revature.models.Payment;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.OrderService;
import com.revature.services.PaymentService;
import com.revature.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(OrderController.class)
public class TestOrderController {

    @MockBean
    private OrderService orderService;

    @MockBean
    private AuthService authService;

    @MockBean
    private UserService userService;

    @MockBean
    PaymentService paymentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test that the OrderController returns all orders")
    public void testGetAllOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1, new User(), new Payment(), Date.valueOf(LocalDate.now()), "1234 Test St"));
        orders.add(new Order(2, new User(), new Payment(), Date.valueOf(LocalDate.now()), "1234 Test St"));

        List<OrderResponse> orderResponses = Arrays.asList(
            new OrderResponse(orders.get(0)),
            new OrderResponse(orders.get(1))
        );
        given(orderService.findAll()).willReturn(orderResponses);

        // perform the request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/order")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andDo(print())
            .andReturn();
    }

    @Test
    @DisplayName("Test that the OrderController creates an order")
    public void testCreateOrder() throws Exception {

        User user = new User();
        user.setId(1);
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("test@gmail.com");
        user.setPassword("password");
        user.setAdmin(false);
        user.setEmail("johndoe@example.com");
        user.setActive(true);

        String token = "authToken";

        when(authService.getUserByAuthToken(token)).thenReturn(user);

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setPaymentId(String.valueOf(1));
        createOrderRequest.setShipmentAddress("1234 Test St");

        OrderResponse response = new OrderResponse();
        response.setOrderId(1);
        response.setOrderDate(Date.valueOf(LocalDate.now()));
        response.setShipmentAddress("1234 Test St");
        response.setPaymentId(String.valueOf(1));
        response.setUserEmail("test@gmail.com");

        when(orderService.createOrder(createOrderRequest, user)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/order")
                .header(HttpHeaders.AUTHORIZATION, token)
                .content(new Gson().toJson(createOrderRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(1))
                .andExpect(jsonPath("$.orderDate").value(Date.valueOf(LocalDate.now()).toString()))
                .andExpect(jsonPath("$.shipmentAddress").value("1234 Test St"))
                .andExpect(jsonPath("$.paymentId").value(String.valueOf(1)))
                .andExpect(jsonPath("$.userEmail").value("test@gmail.com"))
                .andReturn();

    }



    @Test
    @DisplayName("Test that the OrderController gets an order by id")
    public void testGetOrderById() throws Exception {

        Order order = new Order();
        order.setId(1);

        OrderService orderService = mock(OrderService.class);
        when(orderService.findById(1)).thenReturn(order);

        OrderController orderController = new OrderController(orderService, authService);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/order/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("Test that the OrderController gets orders by user")
    public void testGetOrderHistory() throws Exception {
        // mock auth and order services
        AuthService mockAuthService = mock(AuthService.class);
        OrderService mockOrderService = mock(OrderService.class);
        // mock the user and orders
        User user = new User();
        List<OrderResponse> orders = Arrays.asList(new OrderResponse(), new OrderResponse());
        // configure the mock auth service to return user when token is provided
        when(mockAuthService.getUserByAuthToken("mock-token")).thenReturn(user);
        // configure the mock order service to return the user's orders when the user is provided
        when(mockOrderService.findAllUserOrders(user)).thenReturn(orders);
        // create the controller with the mock services
        OrderController orderController = new OrderController(mockOrderService, mockAuthService);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/order/history")
                .header("Authorization", "mock-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(print());
    }

}
