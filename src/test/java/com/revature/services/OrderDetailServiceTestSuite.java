package com.revature.services;

import com.revature.dtos.OrderDetailRequest;
import com.revature.dtos.OrderDetailResponse;
import com.revature.dtos.PaymentResponse;
import com.revature.models.*;
import com.revature.repositories.OrderDetailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import com.revature.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.mockito.Mockito.*;

public class OrderDetailServiceTestSuite {
    private static OrderDetailService sut;
    private static OrderDetailRepository orderDetailRepository;
    private static ProductService productService;
    private static OrderService orderService;

    @BeforeAll
    static void init() {
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
        OrderDetail validOrderDetail = spy(new OrderDetail(1,validOrder,validProduct,1));
        OrderDetailRequest orderDetailRequest = spy(new OrderDetailRequest(1,1,1));
        //orderDetailRepository.save(validOrderDetail);
        OrderDetailResponse validOrderDetailResponse=sut.createOrderDetail(orderDetailRequest);

        //Assertions.assertFalse(orderDetailRepository.existsById(validOrderDetail.getId()));
        Assertions.assertInstanceOf(OrderDetailResponse.class, validOrderDetailResponse);
    }
}
