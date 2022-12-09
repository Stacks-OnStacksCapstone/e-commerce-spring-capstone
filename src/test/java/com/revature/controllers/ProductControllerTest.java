package com.revature.controllers;

import com.revature.ECommerceApplication;
import com.revature.services.AuthService;
import com.revature.services.ProductReviewService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ECommerceApplication.class)
public class ProductControllerTest {
    @MockBean(name = "AuthService")
    private AuthService authService;

    @MockBean(name = "ProductReviewService")
    private ProductReviewService productReviewService;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    // TODO: Add tests for getting all products
    @Test
    public void testGetAllProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // TODO: Add tests for getting a product by ID
    @Test
    public void testGetProductById() throws Exception {
        mockMvc.perform(get("/api//products/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // TODO: Add tests for getting a product by name/keyword
    @Test
    public void testGetProductByKeyword() throws Exception {
        mockMvc.perform(get("/api//products/{keyword}", "shirt"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // TODO: Add tests for puttig a product
    @Test
    public void testPutProduct() throws Exception {
        mockMvc.perform(get("/api//products/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // TODO: Add tests for deleting a product
    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(get("/api//products/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
