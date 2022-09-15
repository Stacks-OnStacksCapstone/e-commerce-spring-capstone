package com.revature.services;

import com.revature.dtos.ProductReviewResponse;
import com.revature.models.ProductReview;
import com.revature.repositories.ProductReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductReviewService {

    private final ProductReviewRepository productReviewRepository;

    public ProductReviewService(ProductReviewRepository productReviewRepository) {
        this.productReviewRepository = productReviewRepository;
    }

    public List<ProductReviewResponse> findAll(){
        return productReviewRepository.findAll().
                                        stream().
                                        map(ProductReviewResponse::new).
                                        collect(Collectors.toList());
    }

    public Optional<ProductReviewResponse> findById(int id) {
        return productReviewRepository.findById(id).
                                       map(ProductReviewResponse::new);
    }

    public List<ProductReviewResponse> findByProductId(int id) {
        return productReviewRepository.findallByProductId(id).
                                        stream().
                                        map(ProductReviewResponse::new).
                                        collect(Collectors.toList());
    }

    public ProductReview save(ProductReview productReview){return productReviewRepository.save(productReview);}

    public void deleteById(int id){productReviewRepository.deleteById(id);}

}