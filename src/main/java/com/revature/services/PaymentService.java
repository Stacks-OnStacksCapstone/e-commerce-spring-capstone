package com.revature.services;

import com.revature.models.Payment;
import com.revature.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment findById(String id) {
        Payment foundPayment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("No payment with this ID found."));
        return foundPayment;
    }
}
