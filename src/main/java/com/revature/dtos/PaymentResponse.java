package com.revature.dtos;

import com.revature.models.Payment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class PaymentResponse {
    private String id;
    private String ccv;
    private Date expDate;
    private String zip;
    private float balance;
    private String userEmail;

    public PaymentResponse(Payment payment) {
        this.id = payment.getId();
        this.ccv = payment.getCcv();
        this.expDate = payment.getExpDate();
        this.zip = payment.getZip();
        this.balance = payment.getBalance();
        this.userEmail = payment.getUserId().getEmail();
    }
}
