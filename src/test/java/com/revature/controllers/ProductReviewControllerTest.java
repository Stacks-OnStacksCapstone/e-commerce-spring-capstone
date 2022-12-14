package com.revature.controllers;

import com.revature.ECommerceApplication;
import com.revature.dtos.Principal;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.ProductReviewService;
import com.revature.services.TokenService;
import com.revature.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

    @Autowired
    @InjectMocks
    private TokenService tokenService;

    @Autowired
    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    public String getToken() throws Exception {
        User user1 = userService.findUserById(1);
        Principal payload = new Principal(user1);
        return tokenService.generateToken(payload);
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

    @Test
    public void testDeleteProductReview() throws Exception {
        this.mockMvc
                .perform(delete("/api/productreview/{id}", 2)
                        .header("Authorization", getToken()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetProductReviewCanPost() throws Exception {
        // {postID, userID}
        int[][] canPostIDs = {{2,1},{3,2},{4,2},{5,2},{6,1},{7,1},{8,1},{9,2},{10,2}, {11,1}};

        for (int[] canPostID : canPostIDs) {
            if(canPostID[1] == 1) { // is admin
                this.mockMvc
                        .perform(get("/api/productreview/post/{post_id}/{user_id}", canPostID[0], canPostID[1])
                                .header("Authorization", getToken()))
                        .andExpect(status().isOk())
                        .andDo(print());
            } else if (canPostID[1] == 2) { // is user
                this.mockMvc
                        .perform(get("/api/productreview/post/{post_id}/{user_id}", canPostID[0], canPostID[1])
                                .header("Authorization", getToken()))
                        .andExpect(status().isOk())
                        .andDo(print());
            }
        }
    }

    @Test
    public void testPutProductReview() throws Exception {
        this.mockMvc
                .perform(put("/api/productreview")
                        .header("Authorization", getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"id\": 2,\n" +
                                "    \"rating\": 5,\n" +
                                "    \"comment\": \"There like clouds on my ears!\",\n" +
                                "    \"postId\": 1\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".id").value(11))
                .andExpect(jsonPath(".rating").value(5))
                .andExpect(jsonPath(".comment").value("There like clouds on my ears!"))
                .andExpect(jsonPath(".postId").value(1))
                .andDo(print());
    }

    @Test
    public void testGetAllProductReview() throws Exception {
        int[] reviewOrder = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] productIDs = {1, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] ratings = {4, 5, 1, 5, 2, 4, 5, 3, 5, 5};
        String[] comments = {
                "This shirt is the best",
                "Holds the things",
                "Cool beans",
                "A very nice coat",
                "These sound like tin cans",
                "A vote for me is a vote for this shirt",
                "Air is the enemy",
                "Nice feather",
                "So puffy its like a cloud",
                "There like clouds on my ears!"
        };
        int[] postIDs = {2, 3, 4, 5, 6, 7, 8, 9, 10, 1};

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
        int[][] doubleIDs = {{1,2},{3,3},{4,4},{5,5},{6,6},{7,7},{8,8},{9,9},{10,10},{11,1}};

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
    public void testGetProductReviewByIDAndRating() throws Exception {
        // {rating, postID}
        int[][] ratingIDs = {{4, 2}, {5, 3}, {1, 4}, {5, 5}, {2, 6}, {4, 7}, {5, 8}, {3, 9}, {5, 10}, {5, 1}};

        for (int[] ratingID : ratingIDs) {
            this.mockMvc
                    .perform(get("/api/productreview/rate/{id}/{rating}", ratingID[1], ratingID[0]))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath(".rating").value(ratingID[0]))
                    .andExpect(jsonPath(".postId").value(ratingID[1]))
                    .andDo(print());
        }
    }

}
