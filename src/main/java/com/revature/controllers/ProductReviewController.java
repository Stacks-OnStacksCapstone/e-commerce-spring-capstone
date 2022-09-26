package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.models.User;
import com.revature.dtos.ProductReviewRequest;
import com.revature.dtos.ProductReviewResponse;
import com.revature.services.ProductReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/productreview")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000", "http://127.0.0.1:3000", "http://e-commerce-congo-react-lb-919946656.us-east-1.elb.amazonaws.com"},  allowCredentials = "true")
public class ProductReviewController {

    private final ProductReviewService productReviewService;


    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @GetMapping
    public ResponseEntity<List<ProductReviewResponse>> getReviews() {
        return ResponseEntity.ok(productReviewService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ProductReviewResponse>> getReviewsByProductId(@PathVariable("id") int id) {
        return ResponseEntity.ok(productReviewService.findByProductId(id));

    }

    @GetMapping("/avr/{id}")
    public ResponseEntity<Integer> getProductAverageScore(@PathVariable("id") int id) {
        return ResponseEntity.ok(productReviewService.findProductAverageScore(id));

    }

    @GetMapping("/rate/{id}/{rating}")
    public ResponseEntity<List<ProductReviewResponse>> getProductByScore(@PathVariable("id") int id,@PathVariable("rating") int rating) {
        return ResponseEntity.ok(productReviewService.findProductByScore(id,rating));

    }

    @Authorized
    @GetMapping("/post/{post_id}/{user_id}")
    public ResponseEntity<Boolean> canPost(@PathVariable("post_id") int post_id,@PathVariable("user_id") int user_id) {
        return ResponseEntity.ok(productReviewService.canPost(post_id,user_id));

    }

    @Authorized
    @PutMapping
    public ResponseEntity<ProductReviewResponse> upsert(@RequestBody ProductReviewRequest productReview, HttpSession session) {
        if(session.getAttribute("user") == null) {
            return ResponseEntity.notFound().build();
        }
        User user = (User)session.getAttribute("user");
        return ResponseEntity.ok(new ProductReviewResponse(productReviewService.save(productReview, user)));
    }

    @Authorized
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id, HttpSession session) {
        if(session.getAttribute("user") == null) {
            return;
        }
        productReviewService.deleteById(id);
    }
}
