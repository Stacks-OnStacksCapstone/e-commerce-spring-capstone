package com.revature.dtos;

import com.revature.models.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {
    private int id;
    private int ordersId;
    private int productId;
    private float quantity;

    public OrderDetailResponse(OrderDetail orderDetail) {
        this.id = orderDetail.getId();
        this.ordersId = orderDetail.getOrderId().getId();
        this.productId = orderDetail.getProductId().getId();
        this.quantity = orderDetail.getQuantity();
    }
}
