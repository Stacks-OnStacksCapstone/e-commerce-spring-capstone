package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.dtos.OrderDetailResponse;
import com.revature.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @Authorized
    @GetMapping("/{id}")
    public OrderDetailResponse findById(@PathVariable int id){
        return orderDetailService.findById(id);
    }

    @Authorized
    @GetMapping
    public ResponseEntity<List<OrderDetailResponse>> findAllOrderDetails(){return ResponseEntity.ok(orderDetailService.findAll());}
}
