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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
        User user = new User(
                1,
                "testuser@gmail.com",
                "Testerson",
                "password",
                "Usertown",
                true,
                true,
                null
        );

        when(productReviewService.findAll()).thenReturn(List.of(
                new ProductReviewResponse(
                        1,
                        4,
                        "This shirt is the best",
                        2,
                        new UserResponse(user)
                ),
                new ProductReviewResponse(
                        9,
                        3,
                        "Nice feather",
                        9,
                        new UserResponse(user)
                )
        ));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/productreview"))
                .andDo(print())
                // Check Shirt Review
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rating").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].review").value("This shirt is the best"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productId").value(2))
                // Check Hat Review
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].rating").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].review").value("Nice feather"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].productId").value(9)
                );
    }
}

