package com.revature.services;

import com.revature.models.Order;
import com.revature.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
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
