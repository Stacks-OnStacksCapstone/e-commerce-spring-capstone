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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ECommerceApplication.class)
@ActiveProfiles("test")
public class ProductReviewControllerTest {
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

    @Test
    public void testGetAllProductReview() throws Exception {
        int[] reviewOrder = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] productIDs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] ratings = {4, 1, 5, 1, 5, 2, 4, 5, 3, 5};
        String[] comments = {
                "This shirt is the best",
                "These sound like tin cans",
                "Holds the things",
                "Cool beans",
                "A very nice coat",
                "These sound like tin cans",
                "A vote for me is a vote for this shirt",
                "Air is the enemy",
                "Nice feather",
                "So puffy its like a cloud"
        };
        int[] postIDs = {2, 1, 3, 4, 5, 6, 7, 8, 9, 10};

        for (int order : reviewOrder) {
            System.out.println("Order: " + order + " Product ID: " + productIDs[order] + " Rating: " + ratings[order] + " Comment: " + comments[order] + " Post ID: " + postIDs[order]);
            this.mockMvc
                    .perform(get("/api/productreview"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("[" + order + "].id").value(productIDs[order]))
                    .andExpect(jsonPath("[" + order + "].rating").value(ratings[order]))
                    .andExpect(jsonPath("[" + order + "].comment").value(comments[order]))
                    .andExpect(jsonPath("[" + order + "].postId").value(postIDs[order]))
                    .andDo(print());
        }
    }

    @Test
    public void testGetProductByID() throws Exception {
        int[] productIDs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (int productID : productIDs) {
            System.out.println("Product ID: " + productID);
            this.mockMvc
                    .perform(get("/api/productreview/{id}")
                    .param("id", String.valueOf(productID)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath(".id").value(productID))
                    .andDo(print());
        }
    }
}

