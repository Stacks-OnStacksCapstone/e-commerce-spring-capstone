package com.revature.controllers;

import com.revature.ECommerceApplication;
import com.revature.dtos.ProductReviewResponse;
import com.revature.dtos.UserResponse;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.ProductReviewService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
        mockMvc.perform(get("/api/productreview")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("    {\n" +
                                "        \"id\": 1,\n" +
                                "        \"rating\": 4,\n" +
                                "        \"comment\": \"This shirt is the best\",\n" +
                                "        \"postId\": 2,\n" +
                                "        \"user\": {\n" +
                                "            \"userId\": 1,\n" +
                                "            \"email\": \"testuser@gmail.com\",\n" +
                                "            \"firstName\": \"Testerson\",\n" +
                                "            \"lastName\": \"Usertown\",\n" +
                                "            \"active\": false,\n" +
                                "            \"admin\": true\n" +
                                "        }\n" +
                                "    }")
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("[0].id").value(1))
                        .andExpect(MockMvcResultMatchers.jsonPath("[0].rating").value(4))
                        .andExpect(MockMvcResultMatchers.jsonPath("[0].comment").value("This shirt is the best"))
                        .andExpect(MockMvcResultMatchers.jsonPath("[0].postId").value(2)
                );
    }
}

