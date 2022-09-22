package com.revature.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@NoArgsConstructor
public class CreateOrderRequest {
    @NotEmpty
    @NotNull
    private String paymentId;
    @NotEmpty
    @NotNull
    private String shipmentAddress;
}
