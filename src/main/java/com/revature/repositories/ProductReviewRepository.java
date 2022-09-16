package com.revature.repositories;


import com.revature.models.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Integer> {

    @Query("FROM ProductReview WHERE product_id = :product_id")
    List<ProductReview> findallByProductId(int product_id);
}
