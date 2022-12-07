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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers= ProductController.class)
public class WebTestProductController {

    @MockBean
    private ProductService productservice;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should List All Posts When Making Get Request to Endpoint - /api/product/")
    public void shouldListAllProducts() throws Exception {

        List<Product> product = new ArrayList<Product>;
        product.add(new Product(1,1,50,"Poop","img/src.gif","Poop",true));
        when(productservice.findAll()).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/"))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();

    }

}
