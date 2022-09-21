package com.revature.services;

import com.revature.models.User;
import com.revature.dtos.ProductReviewRequest;
import com.revature.dtos.ProductReviewResponse;
import com.revature.models.ProductReview;
import com.revature.repositories.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
        return productReviewRepository.findAllByProductId(id).
                                        stream().
                                        map(ProductReviewResponse::new).
                                        collect(Collectors.toList());
    }

    public int findProductAverageScore(int id) {
        ArrayList<Integer> list = new ArrayList<>(productReviewRepository.findProductAverageScore(id));
        double sum=0;
        for(int i: list){
            sum+=i;
        }
        if(list.size()!=0) {
            return (int) sum / list.size();
        }else {
            return 0;
        }
    }

    public List<ProductReviewResponse> findProductByScore(int id,int rating) {
        if(rating>5||rating<1){return null;}
        return productReviewRepository.findAllByProductScore(id,rating).
                stream().
                map(ProductReviewResponse::new).
                collect(Collectors.toList());
    }



    public ProductReview save(ProductReviewRequest productReview, User user) {
        try {
            if(productReview.getRating()<1||productReview.getRating()>5||productReview.getComment().trim().equals(""))
            {return null;}

            return productReviewRepository.save(new ProductReview(productReview,
                    productService.findById(productReview.getPostId()).get(),
                    user));

        }catch (NoSuchElementException e){

            return null;
        }
    }

    public void deleteById(int id){productReviewRepository.deleteById(id);}

}
