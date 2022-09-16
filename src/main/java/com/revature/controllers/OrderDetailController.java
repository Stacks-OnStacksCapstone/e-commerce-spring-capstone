package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.controllers.models.OrderDetail;
import com.revature.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orderdetail")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }
    @Authorized
    @GetMapping
    public ResponseEntity<List<OrderDetail>> findAllOrderDetails(){return ResponseEntity.ok(orderDetailService.findAll());}

    @Authorized
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> findById(@PathVariable("id") int id){
        Optional<OrderDetail> optional = orderDetailService.findById(id);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    @Authorized
    @PostMapping
    public ResponseEntity<OrderDetail> createOrderDetail(OrderDetail orderDetail){
        return ResponseEntity.ok(orderDetailService.save(orderDetail));
    }

    @Authorized
    @PutMapping
    public ResponseEntity<OrderDetail> updateOrderDetail(OrderDetail orderDetail){
        return ResponseEntity.ok(orderDetailService.save(orderDetail));
    }

    @Authorized
    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDetail> deleteOrderDetail(@PathVariable("id") int id){
        Optional<OrderDetail> optional = orderDetailService.findById(id);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        orderDetailService.delete(id);

        return ResponseEntity.ok(optional.get());
    }
}
