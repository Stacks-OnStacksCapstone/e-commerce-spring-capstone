package com.revature.models;

import com.revature.dtos.ProductReviewRequest;
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

    public ProductReview(ProductReviewRequest request, Product product, User user) {
        this.id = request.getId();
        this.rating = request.getRating();
        this.comment = request.getComment();
        this.postId = product;
        this.userId = user;
    }
}
