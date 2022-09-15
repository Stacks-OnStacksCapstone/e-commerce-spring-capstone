package com.revature.dtos;


import com.revature.models.ProductReview;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewResponse {


    private int id;
    private int rating;
    private String comment;
    private int postId;
    private int userId;

    public ProductReviewResponse(ProductReview productReview) {
        this.id = productReview.getId();
        this.rating = productReview.getRating();
        this.comment = productReview.getComment();
        this.postId = productReview.getPostId().getId();
        this.userId = productReview.getUserId().getId();
    }
}
