package com.revature.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class EditPaymentRequest {
    private String paymentId;
    private String ccv;
    private Date expDate;
    private String zip;
}
