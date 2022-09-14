package com.revature.services;

import com.revature.dtos.CreatePaymentRequest;
import com.revature.dtos.PaymentResponse;
import com.revature.exceptions.InvalidUserInputException;
import com.revature.models.Payment;
import com.revature.models.User;
import com.revature.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository, UserService userService) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentResponse createPayment(CreatePaymentRequest createPaymentRequest, User user) {
        System.out.println(createPaymentRequest);
        Payment newPayment = new Payment();
        newPayment.setId(UUID.randomUUID().toString());
        newPayment.setBalance((float) 0.0);
        newPayment.setCcv(createPaymentRequest.getCcv());
        newPayment.setZip(createPaymentRequest.getZip());
        newPayment.setExpDate(createPaymentRequest.getExpDate());
        if (user == null) {
            throw new InvalidUserInputException("No user was provided for payment.");
        }
        newPayment.setUserId(user);

        paymentRepository.save(newPayment);

        return new PaymentResponse(newPayment);
    }
    public Payment findPaymentById(String id) {
        Payment foundPayment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("No payment with this ID found."));
        return foundPayment;
    }
}
