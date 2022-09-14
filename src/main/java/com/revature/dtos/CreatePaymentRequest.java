package com.revature.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class CreatePaymentRequest {
    private String ccv;
    private Date expDate;
    private String zip;
}
