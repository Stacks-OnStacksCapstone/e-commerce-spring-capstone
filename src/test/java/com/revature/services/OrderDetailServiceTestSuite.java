package com.revature.services;

import com.revature.dtos.OrderDetailRequest;
import com.revature.dtos.OrderDetailResponse;
import com.revature.repositories.OrderDetailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;

import com.revature.services.ProductService;
import org.junit.jupiter.api.Test;

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
        OrderDetailRequest orderDetailRequest = new OrderDetailRequest();
        orderDetailRequest.setOrderId(1);
        orderDetailRequest.setQuantity(1);
        orderDetailRequest.setProductId(1);
        OrderDetailResponse orderDetailResponse = sut.createOrderDetail(orderDetailRequest);
        Assertions.assertInstanceOf(OrderDetailResponse.class, orderDetailResponse);
    }
}
