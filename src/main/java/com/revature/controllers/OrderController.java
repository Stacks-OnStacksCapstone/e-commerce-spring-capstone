package com.revature.controllers;
import com.revature.annotations.Authorized;
import com.revature.dtos.CreateOrderRequest;
import com.revature.dtos.OrderResponse;
import com.revature.models.Order;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.OrderService;
import com.revature.services.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000", "http://e-commerce-congo-react-lb-919946656.us-east-1.elb.amazonaws.com"},  allowCredentials = "true", exposedHeaders = "Authorization")
public class OrderController {
    private final OrderService orderService;
    private final AuthService authService;

    public OrderController(OrderService orderService, AuthService authService) {
        this.orderService = orderService;
        this.authService = authService;
    }

    @Authorized
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @Authorized
    @GetMapping("/history")
    public ResponseEntity<List<OrderResponse>> getOrderHistory(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        User user = authService.getUserByAuthToken(token);

        return ResponseEntity.ok(orderService.findAllUserOrders(user));
    }

    @Authorized
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") int id) {
        Optional<Order> optional = Optional.of(orderService.findById(id));
        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }
    @Authorized
    @PostMapping
    public ResponseEntity<OrderResponse> createAnOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest, HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        User user = authService.getUserByAuthToken(token);

        return ResponseEntity.ok(orderService.createOrder(createOrderRequest, user));
    }
//    @Authorized
//    @PutMapping
//    public ResponseEntity<String> update(@RequestBody EditOrderRequest editOrderRequest, HttpSession session) {
//        try {
//            ResponseEntity.ok(orderService.update(editOrderRequest, (User) session.getAttribute("user")));
//            return new ResponseEntity<>("Order changed successfully", HttpStatus.CREATED);
//        } catch (UnauthorizedException e) {
//            return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
//        }
//    }

    // Do we need a PatchMapping???
}
