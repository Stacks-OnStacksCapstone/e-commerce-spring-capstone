package com.revature.dtos;

import com.revature.models.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class OrderResponse {
    private int orderId;
    private String userEmail;
    private String paymentId;
    private Date orderDate;
    private String shipmentAddress;

    public OrderResponse(Order order) {
        this.orderId = order.getId();
        this.userEmail = order.getUserId().getEmail();
        this.paymentId = order.getPaymentId().getId();
        this.orderDate = order.getOrderDate();
        this.shipmentAddress = order.getShipmentAddress();
    }
}
