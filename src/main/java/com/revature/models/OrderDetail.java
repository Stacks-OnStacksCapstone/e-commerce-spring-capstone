package com.revature.models;


import com.revature.dtos.OrderDetailRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderId;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product productId;
    private int quantity;

    public OrderDetail(OrderDetailRequest orderDetailRequest, Order order, Product product){
        this.orderId=order;
        this.productId=product;
        this.quantity=orderDetailRequest.getQuantity();

    }
}
