package com.revature.mock;

import com.revature.controllers.ProductController;
import com.revature.models.Product;
import com.revature.services.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers= ProductController.class)
public class TestProductController {

    @MockBean
    private ProductService productservice;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should List All Products - /api/product/")
    public void shouldListAllProducts() throws Exception {

        List<Product> product = new ArrayList<Product>();
        product.add(new Product(1,1,50,"test","img/src.gif","test",true));
        when(productservice.findAll()).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/"))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].description").value("test"))
                .andExpect(jsonPath("$[0].image").value("img/src.gif"))
                .andExpect(jsonPath("$[0].name").value("test"))
                .andDo(print())
                .andReturn();
    }

}
