package com.revature.services;

import com.revature.dtos.CreateOrderRequest;
import com.revature.dtos.OrderResponse;
import com.revature.models.Order;
import com.revature.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserService userService) {

        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    public OrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        Order newOrder = new Order();
        return new OrderResponse();
    }

    public List<OrderResponse> findAll() {
        ArrayList<OrderResponse> orderResponses = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();
        for (Order o : orders) {
            OrderResponse orderResponse = new OrderResponse(o);
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }

    public Optional<Order> findById(int id) {
        return findById(id);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public boolean delete(int id) {
        Order foundOrder = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order couldn't be deleted."));
        orderRepository.delete(foundOrder);
        return true;
    }
}
