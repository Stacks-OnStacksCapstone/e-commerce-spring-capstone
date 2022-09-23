package com.revature.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@NoArgsConstructor
public class CreatePaymentRequest {
    @NotBlank
    private String ccv;
    private Date expDate;
    @NotBlank
    private String cardNumber;
}
