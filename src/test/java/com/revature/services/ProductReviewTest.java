package com.revature.services;


import com.revature.dtos.ProductReviewRequest;
import com.revature.dtos.ProductReviewResponse;
import com.revature.dtos.UserResponse;
import com.revature.models.Product;
import com.revature.models.ProductReview;
import com.revature.models.User;
import com.revature.repositories.ProductRepository;
import com.revature.repositories.ProductReviewRepository;
import com.revature.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductReviewTest {

    @Mock
    private ProductReviewRepository productReviewRepository;
    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private ProductReviewService productReviewService;

    @Autowired
    @InjectMocks
    private ProductService productService;

    @Autowired
    @InjectMocks
    private UserService userService;

    private ProductReview productReview1,productReview2;

    private ProductReviewRequest productReviewRequest1,productReviewRequest2;

    private ProductReviewResponse productReviewResponse1;

    private Product productValid1, productValid2, productValid3;

    private List<Product> productList1;

    private User userValid1;


    @BeforeEach
    public void setUp(){

        userService=new UserService(userRepository);

        productService= new ProductService(productRepository);

        productReviewService= new ProductReviewService(productReviewRepository,userService,productService);

        productValid1 = new Product(
                999,1,20,"Valid","Valid","Valid",true);

        productValid2 = new Product(
                999,1,20,"Valid","Valid","Valid",true);

        productValid3 = new Product(
                999,1,20,"Valid","Valid","Valid",true);

        userValid1 = new User(999,"Valid","Valid","Valid","Valid",true,true);

        productReview1 = new ProductReview(
                999,5,"Valid", productValid1, userValid1);

        productReview2=new ProductReview(
                0,0,"", productValid1, userValid1);


        productList1 = new ArrayList<>();
        productList1.add(productValid1);
        productList1.add(productValid2);
        productList1.add(productValid3);

        productReviewRequest1= new ProductReviewRequest(
                999,5,"Valid",999);

        productReviewRequest2= new ProductReviewRequest(
                0,0,"",0);

        productReviewResponse1 = new ProductReviewResponse(
                999,5,"Valid",999,new UserResponse(userValid1));
    }

    @AfterEach
    public void tearDown()
    {
        productReview1 = null;
        productReview2 = null;
    }


    @Test
    public void testSaveProductReview_givenValidInput(){

        when(productReviewRepository.save(any())).thenReturn(productReview1);
        when(productRepository.findById(any())).thenReturn(Optional.of((productValid1)));
        productReviewService.save(productReviewRequest1, userValid1);
        verify(productReviewRepository,times(1)).save(any());

    }

    @Test
    public void testSaveProductReview_givenInvalidInput(){

        ProductReview productReviewTemp =  productReviewService.save(productReviewRequest2, userValid1);
        assertNull(productReviewTemp);
    }


    @Test
    public void testFindAllProductReviews() {
        when(productReviewRepository.findAll()).thenReturn(new ArrayList<>());

        List<ProductReviewResponse> products = productReviewService.findAll();
        verify(productReviewRepository,times(1)).findAll();
    }

    @Test
    public void testFindAllProductReviewsBySpecificProduct() {
        ArrayList<ProductReview> arr = new ArrayList<>();
        arr.add(productReview1);
        arr.add(productReview2);

        when(productReviewRepository.findallByProductId(eq(1))).thenReturn(new ArrayList<>());
        when(productReviewRepository.findallByProductId(eq(2))).thenReturn(arr);

        List<ProductReviewResponse> products = productReviewService.findByProductId(1);
        verify(productReviewRepository,times(1)).findallByProductId(1);
        assertEquals(0, products.size());

        products = productReviewService.findByProductId(2);
        verify(productReviewRepository,times(1)).findallByProductId(2);
        assertEquals(2, products.size());
    }
}
