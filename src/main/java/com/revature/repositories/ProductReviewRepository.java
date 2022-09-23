package com.revature.repositories;


import com.revature.models.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Integer> {

    @Query("FROM ProductReview WHERE product_id = :product_id")
    List<ProductReview> findAllByProductId(int product_id);

    @Query("SELECT rating FROM ProductReview WHERE product_id = :product_id")
    List<Integer> findProductAverageScore(int product_id);

    @Query("FROM ProductReview WHERE product_id = :product_id AND rating = :rating")
    List<ProductReview> findAllByProductScore(int product_id,int rating);

    @Query("FROM ProductReview WHERE product_id = :product_id AND user_id = :user_id")
    List<ProductReview> canPost(int product_id,int user_id);

}
