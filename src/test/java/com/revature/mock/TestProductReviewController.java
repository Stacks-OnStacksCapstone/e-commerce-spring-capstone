package com.revature.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.AuthController;
import com.revature.controllers.ProductReviewController;
import com.revature.dtos.ProductReviewRequest;
import com.revature.dtos.ProductReviewResponse;
import com.revature.models.Product;
import com.revature.models.ProductReview;
import com.revature.models.User;
import com.revature.repositories.ProductReviewRepository;
import com.revature.services.AuthService;
import com.revature.services.ProductReviewService;
import com.revature.services.ProductService;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers=ProductReviewController.class)
public class TestProductReviewController {

    @MockBean
    private ProductReviewService productreviewservice;

    @MockBean
    private AuthService authservice;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should List All Product Reviews - /api/productreview/")
    public void shouldListAllProductReviews() throws Exception {

        String jsonString = "{\"id\": 1,\"rating\": 0,\"comment\": \"string\", \"postId\": 0,\"user\": {\"userId\": 0,\"email\": \"string\",\"firstName\": \"string\",\"lastName\": \"string\",\"active\": true,\"admin\": true}}";
        ObjectMapper objectMapper = new ObjectMapper();
        ProductReviewResponse prr = objectMapper.readValue(jsonString,ProductReviewResponse.class);
        List<ProductReviewResponse> prrlist = new ArrayList();
        prrlist.add(prr);
        when(productreviewservice.findAll()).thenReturn(prrlist);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/productreview/"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Should Adjust Product Reviews - /api/productreview/")
    public void shouldAdjustProductReviews() throws Exception {

        //Product Object
        String jsonString1 = "{\"id\":\"1\",\"rating\":\"4\",\"comment\":\"I like cheese\",\"postId\":\"1\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        ProductReviewRequest prr = objectMapper.readValue(jsonString1,ProductReviewRequest.class);

        //User Object
        String jsonString2 = "{\"id\":\"0\",\"email\":\"test@test.com\",\"firstName\":\"first\",\"lastName\":\"last\",\"active\":\"true\",\"admin\":\"true\"}";
        ObjectMapper objectMapper2 = new ObjectMapper();
        User user = objectMapper2.readValue(jsonString2, User.class);

        //Fake Product
        Product product = new Product(1,4,50,"test","test.gif","test",true);

        //Fake Product Review
        ProductReview pr = new ProductReview(1,1,"test",product,user);

        when(authservice.getUserByAuthToken(anyString())).thenReturn(user);
        when(productreviewservice.save(prr,user)).thenReturn(pr);


        mockMvc.perform(MockMvcRequestBuilders.put("/api/productreview/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"id\":\"1\",\"rating\":\"4\",\"comment\":\"I like cheese\",\"postId\":\"1\"}")
                        .header("Authorization",""))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Should List Product Review By ID - /api/productreview/{id}")
    public void shouldListProductReviewById() throws Exception {
        String jsonString = "{\"id\": 1,\"rating\": 0,\"comment\": \"string\", \"postId\": 0,\"user\": {\"userId\": 0,\"email\": \"string\",\"firstName\": \"string\",\"lastName\": \"string\",\"active\": true,\"admin\": true}}";
        ObjectMapper objectMapper = new ObjectMapper();
        ProductReviewResponse prr = objectMapper.readValue(jsonString,ProductReviewResponse.class);
        List<ProductReviewResponse> prrlist = new ArrayList();
        prrlist.add(prr);
        when(productreviewservice.findByProductId(1)).thenReturn(prrlist);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/productreview/1"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Should List Product Review By ID & Rating - /api/productreview/rate/{id}/{rating}")
    public void shouldListProductReviewByIdAndRating() throws Exception {
        String jsonString = "{\"id\": 1,\"rating\": 0,\"comment\": \"string\", \"postId\": 0,\"user\": {\"userId\": 0,\"email\": \"string\",\"firstName\": \"string\",\"lastName\": \"string\",\"active\": true,\"admin\": true}}";
        ObjectMapper objectMapper = new ObjectMapper();
        ProductReviewResponse prr = objectMapper.readValue(jsonString,ProductReviewResponse.class);
        List<ProductReviewResponse> prrlist = new ArrayList();
        prrlist.add(prr);
        when(productreviewservice.findProductByScore(1,1)).thenReturn(prrlist);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/productreview/rate/1/1"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Should Delete Product Review By ID - /api/productreview/{id}")
    public void shouldDeleteProductReviewById() throws Exception {

        doNothing().when(productreviewservice).deleteById(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/productreview/1"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Should check if user can post - /api/productreview/post/{post_id}/{user_id}")
    public void shouldCheckIfUserCanPost() throws Exception {

        when(productreviewservice.canPost(1,1)).thenReturn(true);


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/productreview/post/1/1"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();

        Assert.assertEquals("Error: The response is not true","true",result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Should check product average - /api/productreview/avr/{id}")
    public void shouldCheckReviewAverage() throws Exception {

        when(productreviewservice.findProductAverageScore(1)).thenReturn(5);


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/productreview/avr/1"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();

        Assert.assertEquals("Error: The response is not 5","5",result.getResponse().getContentAsString());
    }

}
