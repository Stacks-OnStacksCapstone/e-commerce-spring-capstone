package com.revature.mock;

import com.revature.controllers.ProductController;
import com.revature.models.Product;
import com.revature.services.AuthService;
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
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers= ProductController.class)
public class TestProductController {

    @MockBean
    private ProductService productservice;

    @MockBean
    private AuthService authservice;

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

    @Test
    @DisplayName("Should List Product By ID - /api/product/{id}")
    public void shouldListProductByID() throws Exception {

        Product product = new Product(1,1,50,"test","img/src.gif","test",true);
        when(productservice.findById(1)).thenReturn(Optional.of(product));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/1"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.description").value("test"))
                .andExpect(jsonPath("$.image").value("img/src.gif"))
                .andExpect(jsonPath("$.name").value("test"))
                .andDo(print())
                .andReturn();
    }


    @Test
    @DisplayName("Should Delete Product By ID - /api/product/{id}")
    public void shouldDeleteProductByID() throws Exception {

        Product product = new Product(1,1,50,"test","img/src.gif","test",true);
        when(productservice.findById(1)).thenReturn(Optional.of(product));
        doNothing().when(productservice).delete(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/product/1"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.description").value("test"))
                .andExpect(jsonPath("$.image").value("img/src.gif"))
                .andExpect(jsonPath("$.name").value("test"))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Should Save Product By ID - /api/product/")
    public void shouldSaveProductByID() throws Exception {

        Product product1 = new Product(1,5,50,"Product LOL","test.gif","Skateboard",true);
        Product product2 = new Product(0,1,50,"test2","img/src.gif","test2",true);
        when(productservice.save(product1)).thenReturn(product2);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/product/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"id\":\"1\",\"quantity\":\"5\",\"price\":\"50\",\"description\":\"Product LOL\",\"image\":\"test.gif\",\"name\":\"Skateboard\",\"isActive\":\"true\"}"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.description").value("test2"))
                .andExpect(jsonPath("$.image").value("img/src.gif"))
                .andExpect(jsonPath("$.name").value("test2"))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Should Search Product By Keyword - /api/product/search/")
    public void shouldSearchProductByKeyword() throws Exception {

        List<Product> product = new ArrayList<Product>();
        product.add(new Product(1,5,50,"test2","test.gif","test2",true));
        when(productservice.findByKeyword("cheese")).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/search/cheese"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].description").value("test2"))
                .andExpect(jsonPath("$[0].image").value("test.gif"))
                .andExpect(jsonPath("$[0].name").value("test2"))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Should Edit Product Quantity By ID - /api/product/")
    public void shouldEditProductQuantityByID() throws Exception {

        Product product1 = new Product(1,5,50,"Product LOL","test.gif","Skateboard",true);
        Product product2 = new Product(1,5,50,"test2","img/src.gif","test2",true);
        when(productservice.findById(1)).thenReturn(Optional.of(product2));

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/product/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("[{\"id\":\"1\",\"quantity\":\"1\"}]"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].description").value("test2"))
                .andExpect(jsonPath("$[0].image").value("img/src.gif"))
                .andExpect(jsonPath("$[0].name").value("test2"))
                .andDo(print())
                .andReturn();
    }

}
