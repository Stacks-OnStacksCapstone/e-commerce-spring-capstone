package com.revature.services;

import com.revature.models.Product;
import com.revature.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ProductServiceTestSuite {

    ProductService sut;
    ProductRepository mockProductRepository;

    @BeforeEach
    public void testPrep(){
        mockProductRepository = mock(ProductRepository.class);
        sut = new ProductService(mockProductRepository);
    }

    @Test
    public void test_save_returnsNewProduct_givenValidProduct(){
        Product newProduct = new Product(777,
        12,
        99.99,
        "This new product is very expensive",
        "https://images.ctfassets.net/5gvckmvm9289/3BlDoZxSSjqAvv1jBJP7TH/65f9a95484117730ace42abf64e89572/Noissue-x-Creatsy-Tote-Bag-Mockup-Bundle-_4_-2.png",
        "New Valid Product",
        true);

        when(mockProductRepository.save(newProduct)).thenReturn(newProduct);

        Product returnedProduct = sut.save(newProduct);

        Assertions.assertInstanceOf(Product.class, returnedProduct);
        verify(mockProductRepository, times(1));
    }
}
