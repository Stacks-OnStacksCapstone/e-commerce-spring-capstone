package com.revature.controllers;
import com.revature.annotations.Authorized;
import com.revature.dtos.CreateOrderRequest;
import com.revature.dtos.OrderResponse;
import com.revature.models.Order;
import com.revature.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Authorized
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @Authorized
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") int id) {
        Optional<Order> optional = orderService.findById(id);
        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }
    @Authorized
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest order) {
        Order newOrder = new Order();
        return ResponseEntity.ok(orderService.save(newOrder));
    }
    @Authorized
    @PutMapping
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.save(order));
    }

    // Do we need a PatchMapping???

    @Authorized
    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable("id") int id) {
        Optional<Order> optional = orderService.findById(id);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        orderService.delete(id);

        return ResponseEntity.ok(optional.get());
    }
}
