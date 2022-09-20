package com.revature.services;

import com.revature.dtos.CreateOrderRequest;
import com.revature.dtos.OrderDetailRequest;
import com.revature.dtos.OrderDetailResponse;
import com.revature.models.*;
import com.revature.repositories.OrderDetailRepository;
import com.revature.repositories.OrderRepository;
import com.revature.repositories.PaymentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;


import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class OrderDetailServiceTestSuite {
    private static OrderDetailService sut;
    private static OrderDetailRepository orderDetailRepository;
    private static OrderRepository orderRepository;
    private static PaymentRepository paymentRepository;
    private static ProductService productService;
    private static OrderService orderService;

    private static PaymentService paymentService;


    @BeforeAll
    static void init() {

        orderRepository = mock(OrderRepository.class);
        orderDetailRepository = mock(OrderDetailRepository.class);
        productService = mock(ProductService.class);
        orderService = mock(OrderService.class);
        sut = new OrderDetailService(orderDetailRepository, productService, orderService);
    }
    @Test
    public void test_createOrderDetail_returnOrderDetailResponse_givenValidCreateOrderDetailRequest(){
        User validUser = spy(new User(1, "valid", "valid", "valid", "valid", true, true));
        Payment validPayment = spy(new Payment("1", "valid", new Date(2000,12,12), "12345", (float) 0.01, validUser));
        Product validProduct = spy(new Product(1,1,1,"valid","valid","valid",true));
        Order validOrder = spy(new Order(1, validUser, validPayment, new Date(2000,12,12), "valid"));
        OrderDetailRequest orderDetailRequest = spy(new OrderDetailRequest(1,1,1));

        when(orderService.findById(validOrder.getId())).thenReturn(Optional.of(validOrder));
        when(productService.findById(orderDetailRequest.getProductId())).thenReturn(Optional.of(validProduct));
        doReturn(new OrderDetail(orderDetailRequest, validOrder, validProduct)).when(orderDetailRepository).save(any(OrderDetail.class));

        OrderDetailResponse validOrderDetailResponse=sut.createOrderDetail(orderDetailRequest);
        Assertions.assertInstanceOf(OrderDetailResponse.class, validOrderDetailResponse);

        verify(orderDetailRepository, times(1));

    }
}
