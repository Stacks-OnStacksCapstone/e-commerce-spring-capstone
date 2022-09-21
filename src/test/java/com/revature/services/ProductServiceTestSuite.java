package com.revature.services;

import com.revature.models.Product;
import com.revature.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ProductServiceTestSuite {

    static ProductService sut;
    static ProductRepository mockProductRepository;

    @BeforeAll
    public static void testPrep(){
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
        newProduct.setId(2);
        Product returnedProduct = sut.save(newProduct);

        Assertions.assertInstanceOf(Product.class, returnedProduct);
        verify(mockProductRepository, times(1));
    }

    @Test
    public void test_findById_returnsProduct_givenValidId(){
        Product newProduct = new Product(1,
                10,
                20.00,
                "A nice pair of headphones",
                "https://i.insider.com/54eb437f6bb3f7697f85da71?width=1000&format=jpeg&auto=webp",
                "Headphones",
                true);

        when(mockProductRepository.findById(eq(1))).thenReturn(Optional.of(newProduct));

        Product returnedProduct = sut.findById(1).get();

        Assertions.assertInstanceOf(Product.class, returnedProduct);
        verify(mockProductRepository, times(1));
    }
    @Test

    public void test_findAll_returnsAllProducts(){
        List<Product> products = new ArrayList<>();
        Product newProduct = new Product(1,
                10,
                20.00,
                "A nice pair of headphones",
                "https://i.insider.com/54eb437f6bb3f7697f85da71?width=1000&format=jpeg&auto=webp",
                "Headphones",
                true);
        products.add(newProduct);
        when(mockProductRepository.findAll()).thenReturn(products);

        List<Product> result = sut.findAll();
        for(Product p : result){
            Assertions.assertInstanceOf(Product.class, p);
        }

        verify(mockProductRepository, times(1));
    }
}
