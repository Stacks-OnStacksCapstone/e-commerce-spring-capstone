package com.revature.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
public class EditOrderRequest {
    private int orderId;
    @Nullable
    private String paymentId;
    @Nullable
    private String shipmentAddress;
}
