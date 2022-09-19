package com.revature.services;

import com.revature.models.User;
import com.revature.dtos.ProductReviewRequest;
import com.revature.dtos.ProductReviewResponse;
import com.revature.models.ProductReview;
import com.revature.repositories.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductReviewService {

    private final ProductReviewRepository productReviewRepository;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public ProductReviewService(ProductReviewRepository productReviewRepository, UserService userService, ProductService productService) {
        this.productReviewRepository = productReviewRepository;
        this.userService = userService;
        this.productService = productService;
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

    public ProductReview save(ProductReviewRequest productReview, User user) {
        return productReviewRepository.save(new ProductReview(productReview,
                productService.findById(productReview.getPostId()).get(),
                user));
    }

    public void deleteById(int id){productReviewRepository.deleteById(id);}

}
