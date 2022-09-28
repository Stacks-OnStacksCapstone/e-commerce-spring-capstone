package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.dtos.OrderDetailRequest;
import com.revature.dtos.OrderDetailResponse;
import com.revature.models.Order;
import com.revature.models.OrderDetail;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orderdetail")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000", "http://e-commerce-congo-react-lb-919946656.us-east-1.elb.amazonaws.com"},  allowCredentials = "true", exposedHeaders = "Authorization")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;
    private final AuthService authService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService, AuthService authService) {
        this.orderDetailService = orderDetailService;
        this.authService = authService;
    }

    @Authorized
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> findById(@PathVariable("id") int id, HttpServletRequest req){
        String token = req.getHeader("Authorization");
        User user = authService.getUserByAuthToken(token);

        Optional<OrderDetail> optional = orderDetailService.findById(id);
        if(!optional.isPresent() || (optional.get().getOrderId().getUserId().getId() != user.getId())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    @Authorized
    @GetMapping("/order/{id}")
    public ResponseEntity<List<OrderDetailResponse>> findAllByOrderId(@PathVariable("id") int id, HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        User user = authService.getUserByAuthToken(token);

        List<OrderDetailResponse> orderDetailResponses = orderDetailService.findAllOrderDetailsByOrder(id);
        if (orderDetailResponses.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        Order order = orderDetailService.findById( orderDetailResponses.get(0).getId()).get().getOrderId();
        if (user.getId() != order.getUserId().getId()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderDetailResponses);
    }

    @Authorized
    @PostMapping
    public ResponseEntity<OrderDetailResponse> createOrderDetail(@RequestBody OrderDetailRequest orderDetailRequest){
        return ResponseEntity.ok(orderDetailService.createOrderDetail(orderDetailRequest));
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
