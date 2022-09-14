package com.revature.dtos;

import com.revature.models.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class OrderResponse {
    private String userEmail;
    private String paymentId;
    private Date orderDate;
    private String shipmentAddress;
    private boolean orderFulfilled;

    public OrderResponse(Order order) {
        this.userEmail = order.getUserId().getEmail();
        this.paymentId = order.getPaymentId();
        this.orderDate = order.getOrderDate();
        this.shipmentAddress = order.getShipmentAddress();
        this.orderFulfilled = order.isOrderFulfilled();
    }
}
