package com.revature.models;

import com.revature.dtos.CreateOrderRequest;
import com.revature.dtos.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;
    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment paymentId;
    private Date orderDate;
    private String shipmentAddress;

    public Order(CreateOrderRequest createOrderRequest, User user, Payment payment){
        this.userId = user;
        this.paymentId = payment;
        this.shipmentAddress = createOrderRequest.getShipmentAddress();
    }

    public Order(OrderResponse orderResponse){
        this.orderDate = orderResponse.getOrderDate();
        this.shipmentAddress = orderResponse.getShipmentAddress();
    }
}
