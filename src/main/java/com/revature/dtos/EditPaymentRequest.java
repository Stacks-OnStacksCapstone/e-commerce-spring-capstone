package com.revature.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@NoArgsConstructor
public class EditPaymentRequest {
    private String paymentId;
    @NotBlank
    private String cardType;
    @NotBlank
    private Date expDate;
    @NotBlank
    private String cardNumber;
}
