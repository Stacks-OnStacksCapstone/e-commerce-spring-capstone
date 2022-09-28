package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.dtos.CreatePaymentRequest;
import com.revature.dtos.EditPaymentRequest;
import com.revature.dtos.PaymentResponse;
import com.revature.dtos.UserResponse;
import com.revature.exceptions.UnauthorizedException;
import com.revature.models.Payment;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000", "http://e-commerce-congo-react-lb-919946656.us-east-1.elb.amazonaws.com"},  allowCredentials = "true", exposedHeaders = "Authorization")
public class PaymentController {

    private final PaymentService paymentService;
    private final AuthService authService;

    public PaymentController(PaymentService paymentService, AuthService authService) {
        this.paymentService = paymentService;
        this.authService = authService;
    }

    @Authorized
    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody @Valid CreatePaymentRequest createPaymentRequest, HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        User authUser = authService.getUserByAuthToken(token);

        PaymentResponse newPayment = paymentService.createPayment(createPaymentRequest, authUser);
        return ResponseEntity.ok(newPayment);
    }

    @Authorized
    @PutMapping
    public ResponseEntity<PaymentResponse> updatedPayment(@RequestBody EditPaymentRequest editPaymentRequest, HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        User user = authService.getUserByAuthToken(token);

        return ResponseEntity.ok(paymentService.updatePayment(editPaymentRequest, user));
    }

    @Authorized
    @DeleteMapping
    public ResponseEntity<String> deletePayment(@RequestParam("paymentId") String paymentId, HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        User user = authService.getUserByAuthToken(token);

        try {
            if (paymentService.deletePayment(paymentId, user) != null) {
                return ResponseEntity.ok(String.format("Payment %s was deleted", paymentId));
            }
        } catch (UnauthorizedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Payment not deleted", HttpStatus.BAD_REQUEST);
    }

    @Authorized
    @GetMapping
    public List<PaymentResponse> findAllUserPayment(HttpServletRequest req){
        String token = req.getHeader("Authorization");
        User user = authService.getUserByAuthToken(token);

        List<PaymentResponse> newPaymentResponse = paymentService.findAllByUser(user.getId());
        return newPaymentResponse;
    }
}
