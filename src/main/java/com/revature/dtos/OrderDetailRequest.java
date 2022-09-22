package com.revature.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailRequest {
    private int productId;
    private int orderId;
    private int quantity;
}
