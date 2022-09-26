package com.revature.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class EditOrderRequest {
    private int orderId;
    @NotBlank
    private String paymentId;
    @NotBlank
    private String shipmentAddress;
}
