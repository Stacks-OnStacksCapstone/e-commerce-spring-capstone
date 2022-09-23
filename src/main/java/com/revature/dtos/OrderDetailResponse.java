package com.revature.dtos;

import com.revature.models.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {
    private int id;
    private int ordersId;
    private int productId;
    @NotBlank
    @Min(value = 0)
    private float quantity;

    public OrderDetailResponse(OrderDetail orderDetail) {
        this.id = orderDetail.getId();
        this.ordersId = orderDetail.getOrderId().getId();
        this.productId = orderDetail.getProductId().getId();
        this.quantity = orderDetail.getQuantity();
    }
}
