package com.revature.services;


import com.revature.dtos.CreatePaymentRequest;
import com.revature.dtos.PaymentResponse;
import com.revature.exceptions.InvalidUserInputException;
import com.revature.models.Payment;
import com.revature.models.User;
import com.revature.repositories.PaymentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.sql.Date;

import static org.mockito.Mockito.*;

public class PaymentServiceTestSuite {
    private static PaymentService sut;
    private static UserService userService;
    private static PaymentRepository paymentRepository;

    @BeforeAll
    static void setup() {
        paymentRepository = mock(PaymentRepository.class);
        userService = mock(UserService.class);

        sut = new PaymentService(paymentRepository, userService);
    }

    @Test
    void test_createPayment_returnPaymentResponse_givenValidInput() {
        CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest();
        createPaymentRequest.setCcv("111");
        createPaymentRequest.setExpDate(new Date(System.currentTimeMillis()));
        createPaymentRequest.setCardNumber("1111111111");
        User user = new User();
        PaymentResponse paymentResponse = sut.createPayment(createPaymentRequest, user);
        Assertions.assertInstanceOf(PaymentResponse.class, paymentResponse);
    }

    @Test
    void test_createPayment_throwException_givenInvalidInput() {
        CreatePaymentRequest createPaymentRequest1 = new CreatePaymentRequest();
        createPaymentRequest1.setCcv("111");;
        createPaymentRequest1.setCardNumber("1111111111");

        CreatePaymentRequest createPaymentRequest2 = new CreatePaymentRequest();
        createPaymentRequest2.setCcv("111");
        createPaymentRequest2.setExpDate(new Date(System.currentTimeMillis()));

        CreatePaymentRequest createPaymentRequest3 = new CreatePaymentRequest();
        createPaymentRequest3.setCardNumber("111111111111");
        createPaymentRequest3.setExpDate(new Date(System.currentTimeMillis()));

        User user = new User();
        Assertions.assertThrows(InvalidUserInputException.class, () -> sut.createPayment(createPaymentRequest1, user));
        Assertions.assertThrows(InvalidUserInputException.class, () -> sut.createPayment(createPaymentRequest2, user));
        Assertions.assertThrows(InvalidUserInputException.class, () -> sut.createPayment(createPaymentRequest3, user));
    }

}
