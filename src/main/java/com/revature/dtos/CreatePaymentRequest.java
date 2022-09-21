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
    @Size(min = 3, max = 3)
    private String ccv;
    @NotBlank
    private Date expDate;
    @NotBlank
    @Size(min = 5, max = 5)
    private String zip;
}
