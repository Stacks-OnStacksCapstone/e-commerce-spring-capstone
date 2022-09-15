package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int rating;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
}
