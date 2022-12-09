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


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
    public void testGetProductReviewByID() throws Exception {
        // {productID, postID}
        int[][] doubleIDs = {{1,2},{2,1},{3,3},{4,4},{5,5},{6,6},{7,7},{8,8},{9,9},{10,10}};

        for (int[] doubleID : doubleIDs) {
            this.mockMvc
                    .perform(get("/api/productreview/{id}", doubleID[1]))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("[0].id").value(doubleID[0]))
                    .andExpect(jsonPath("[0].postId").value(doubleID[1]))
                    .andDo(print());
            }
        }

    @Test
    public void testGetProductAverageRating() throws Exception {
        // {productID, averageRating}
        int[][] doublesAverages = {{1, 1},{2, 4},{3, 5},{4, 1},{5, 5},{6, 2},{7, 4},{8, 5},{9, 3},{10, 5}};

        for (int[] doublesAverage : doublesAverages) {
            this.mockMvc
                    .perform(get("/api/productreview/avr/{id}", doublesAverage[0]))
                    .andExpect(status().isOk())
                    .andExpect(content().string(String.valueOf(doublesAverage[1])))
                    .andDo(print());
        }
    }

    // TODO: Add tests for checking if a user or admin is posting a review
    @Test
    public void testGetProductReviewCanPost() throws Exception {
        // {postID, userID}
        int[][] canPostIDs = {{2,1},{1,1},{3,2},{4,2},{5,2},{6,1},{7,1},{8,1},{9,2},{10,2}};

        for (int[] canPostID : canPostIDs) {
            if(canPostID[1] == 1) { // is admin
                this.mockMvc
                        .perform(get("/api/productreview/post/{post_id}/{user_id}", canPostID[0], canPostID[1]))
                        .andExpect(status().isOk())
                        .andExpect(content().string("false"))
                        .andDo(print());
            } else if (canPostID[1] == 2) { // is user
                this.mockMvc
                        .perform(get("/api/productreview/post/{post_id}/{user_id}", canPostID[0], canPostID[1]))
                        .andExpect(status().isOk())
                        .andExpect(content().string("true"))
                        .andDo(print());
            }
        }
    }

    @Test
    public void testGetProductReviewByIDAndRating() throws Exception {
        // {rating, postID}
        int[][] ratingIDs = {{4, 2}, {1, 1}, {5, 3}, {1, 4}, {5, 5}, {2, 6}, {4, 7}, {5, 8}, {3, 9}, {5, 10}};

        for (int[] ratingID : ratingIDs) {
            this.mockMvc
                    .perform(get("/api/productreview/rate/{id}/{rating}", ratingID[1], ratingID[0]))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath(".rating").value(ratingID[0]))
                    .andExpect(jsonPath(".postId").value(ratingID[1]))
                    .andDo(print());
        }
    }

    // TODO: Add test for posting product review
    @Test
    public void testPutProductReview() throws Exception {
        // {id, rating, comment}
        String[][] ratingComments = {
                {"1", "4", "This shirt is the best"},
                {"2", "1", "These sound like tin cans"},
                {"3", "5", "Holds the things"},
                {"4", "1", "Cool beans"},
                {"5", "5", "A very nice coat"},
                {"6", "2", "These sound like tin cans"},
                {"7", "4", "A vote for me is a vote for this shirt"},
                {"8", "5", "Air is the enemy"},
                {"9", "3", "Nice feather"},
                {"10", "5", "So puffy its like a cloud"}
        };

        for (String[] ratingComment : ratingComments) {
            this.mockMvc
                    .perform(put("/api/productreview/rate/{id}/{rating}", ratingComment[0], ratingComment[1]))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath(".rating").value(ratingComment[0]))
                    .andExpect(jsonPath(".comment").value(ratingComment[1]))
                    .andDo(print());
        }
    }

    // TODO: Add test for deleting product review
    @Test
    public void testDeleteProductReview() throws Exception {
        // {id}
        int[] ids = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (int id : ids) {
            this.mockMvc
                    .perform(delete("/api/productreview/{id}", id))
                    .andExpect(status().isOk())
                    .andDo(print());
        }
    }
}
