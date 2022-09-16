package com.revature.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@NoArgsConstructor
public class CreatePaymentRequest {
    @NotEmpty
    @NotNull
    private String ccv;
    @NotNull
    private Date expDate;
    @NotEmpty
    @NotNull
    private String zip;
}
