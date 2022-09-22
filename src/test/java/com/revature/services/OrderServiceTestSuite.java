package com.revature.services;

import com.revature.dtos.CreateOrderRequest;
import com.revature.dtos.OrderResponse;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.Order;
import com.revature.models.Payment;
import com.revature.models.User;
import com.revature.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void test_findAll_returnListOrderResponse(){
        List<Order> orders = new ArrayList<>();

        sut.findAll();
        verify(mockOrderRepository, times(1));
    }

    @Test
    public void test_findById_returnOrderResponse_givenValidId(){
        User validUser = spy(new User(1, "valid", "valid", "valid", "valid", true, true, "valid"));
        Payment validPayment = spy(new Payment("1", "valid", new Date(2000,12,12), "12345", (float) 0.01, validUser));
        Order validOrder = spy(new Order(1, validUser, validPayment, new Date(2000,12,12), "valid"));

        when(mockOrderRepository.findById(1)).thenReturn(Optional.of(validOrder));

        Order actualOrder = sut.findById(1);

        Assertions.assertInstanceOf(Order.class, actualOrder);
        verify(mockOrderRepository, times(1));
    }

    @Test
    public void test_findById_throwResourceNotFoundException_givenInvalidId(){
        when(mockOrderRepository.findById(-10)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> sut.findById(-10));

        verify(mockOrderRepository, times(1));
    }
}
