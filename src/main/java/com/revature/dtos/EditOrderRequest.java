package com.revature.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditOrderRequest {
    private String paymentId;
    private String shipmentAddress;
}
