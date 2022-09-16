package com.revature.services;

import com.revature.dtos.CreatePaymentRequest;
import com.revature.dtos.EditPaymentRequest;
import com.revature.dtos.PaymentResponse;
import com.revature.exceptions.InvalidUserInputException;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.exceptions.UnauthorizedException;
import com.revature.models.Payment;
import com.revature.models.User;
import com.revature.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository, UserService userService) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentResponse createPayment(CreatePaymentRequest createPaymentRequest, User user) {
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

    public PaymentResponse updatePayment(EditPaymentRequest editPaymentRequest, User user) {
        Payment foundPayment = paymentRepository.findById(editPaymentRequest.getPaymentId()).orElseThrow(() -> new ResourceNotFoundException("Payment not found."));
        if (foundPayment.getUserId().getId() != user.getId()) {
            throw new UnauthorizedException("Unauthorized update payment request.");
        }
        foundPayment.setCcv(editPaymentRequest.getCcv());
        foundPayment.setZip(editPaymentRequest.getZip());
        foundPayment.setExpDate(editPaymentRequest.getExpDate());
        paymentRepository.save(foundPayment);
        PaymentResponse updatedPayment = new PaymentResponse(foundPayment);

        return updatedPayment;
    }

    public PaymentResponse deletePayment(String paymentId, User user) throws UnauthorizedException {
        Payment foundPayment = paymentRepository.findById(paymentId).orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        if (foundPayment.getUserId().getId() != user.getId()) {
            throw new UnauthorizedException("Must be logged in as payment owner to delete payment.");
        }
        PaymentResponse paymentResponse = new PaymentResponse(foundPayment);
        paymentRepository.delete(foundPayment);
        return paymentResponse;
    }
}
