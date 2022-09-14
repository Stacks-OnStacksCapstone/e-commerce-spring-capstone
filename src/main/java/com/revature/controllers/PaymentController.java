package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.dtos.CreatePaymentRequest;
import com.revature.dtos.EditPaymentRequest;
import com.revature.dtos.PaymentResponse;
import com.revature.models.Payment;
import com.revature.models.User;
import com.revature.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Authorized
    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody CreatePaymentRequest createPaymentRequest, HttpSession httpSession) {
        User authUser = (User) httpSession.getAttribute("user");
        PaymentResponse newPayment = paymentService.createPayment(createPaymentRequest, authUser);
        return ResponseEntity.ok(newPayment);
    }

    @Authorized
    @PutMapping
    public ResponseEntity<PaymentResponse> updatedPayment(@RequestBody EditPaymentRequest editPaymentRequest, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        return ResponseEntity.ok(paymentService.updatePayment(editPaymentRequest, user));
    }
}
