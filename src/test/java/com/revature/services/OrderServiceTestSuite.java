package com.revature.services;

import com.revature.dtos.CreateOrderRequest;
import com.revature.dtos.OrderResponse;
import com.revature.models.Order;
import com.revature.models.Payment;
import com.revature.models.User;
import com.revature.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.mockito.Mockito.*;
public class OrderServiceTestSuite {

    OrderService sut;
    OrderRepository mockOrderRepository;
    UserService mockUserService;
    PaymentService mockPaymentService;

    @BeforeEach
    public void testPrep(){
        mockOrderRepository = mock(OrderRepository.class);
        mockUserService = mock(UserService.class);
        mockPaymentService = mock(PaymentService.class);
        sut = new OrderService(mockOrderRepository, mockUserService, mockPaymentService);
    }

    @Test
    public void test_createOrder_returnNewOrder_givenValidCreateOrderRequest(){
        User validUser = spy(new User(1, "valid", "valid", "valid", "valid", true, true));
        Payment validPayment = spy(new Payment("1", "valid", new Date(2000,12,12), "12345", (float) 0.01, validUser));
        Order validOrder = spy(new Order(1, validUser, validPayment, new Date(2000,12,12), "valid"));
        CreateOrderRequest  createOrderRequest = spy(new CreateOrderRequest("1","valid"));

        when(mockOrderRepository.existsById(validOrder.getId())).thenReturn(false);
        doReturn(new Order(createOrderRequest, validUser, validPayment)).when(mockOrderRepository).save(any(Order.class));

        OrderResponse actualOrderResponse = sut.createOrder(createOrderRequest, validUser);

        Assertions.assertInstanceOf(OrderResponse.class, actualOrderResponse);
        verify(mockOrderRepository, times(2));
    }

}
