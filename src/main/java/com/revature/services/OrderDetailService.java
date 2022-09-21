package com.revature.services;
import com.revature.dtos.OrderDetailRequest;
import com.revature.dtos.OrderDetailResponse;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.Order;
import com.revature.models.OrderDetail;
import com.revature.models.Product;
import com.revature.models.User;
import com.revature.repositories.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository, ProductService productService, OrderService orderService){
        this.orderDetailRepository=orderDetailRepository;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Transactional
    public OrderDetailResponse createOrderDetail(OrderDetailRequest orderDetailRequest) {
        OrderDetail orderDetail = new OrderDetail();
        Order foundOrder = orderService.findById(orderDetailRequest.getOrderId());
        Product foundProduct = productService.findById(orderDetailRequest.getProductId()).orElseThrow(() -> new ResourceNotFoundException("No product found ."));
        orderDetail.setOrderId(foundOrder);
        orderDetail.setProductId(foundProduct);
        orderDetail.setQuantity(orderDetailRequest.getQuantity());
        orderDetailRepository.save(orderDetail);
        return new OrderDetailResponse(orderDetail);
    }

    @Transactional
    public List<OrderDetail> findAll(){
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        return orderDetailRepository.findAll();
    }

    @Transactional
    public List<OrderDetailResponse> findAllOrderDetailsByOrder(int id){
        Order foundOrder = orderService.findById(id);
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(foundOrder);
        List<OrderDetailResponse> orderDetailResponses = orderDetails.stream().map(OrderDetailResponse::new).collect(Collectors.toList());
        return orderDetailResponses;
    }

    @Transactional
    public Optional<OrderDetail> findById(int id) {
        return orderDetailRepository.findById(id);
    }

    @Transactional
    public boolean delete(int id) {
        OrderDetail foundOrderDetail = orderDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("OrderDetail couldn't be deleted."));
        orderDetailRepository.delete(foundOrderDetail);
        return true;
    }

}
