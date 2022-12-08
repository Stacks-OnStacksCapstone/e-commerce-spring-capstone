package com.revature.controllers;

import com.revature.models.Product;
import com.revature.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @MockBean(name="ProductService")
    private ProductService productService;

    @Autowired private MockMvc mockMvc;

    // Test Collection of Products [headphone1, shirt2, mask, hat1, coat1]
    // /api/product
    @Test
    public void testGetAllProducts() throws Exception {
        when(productService.findAll()).thenReturn(List.of(
                // id, name, description, price, quantity, active
                // headphone1
                new Product(
                1,
                10,
                20.0,
                "A nice pair of headphones",
                "https://i.insider.com/54eb437f6bb3f7697f85da71?width=1000&format=jpeg&auto=webp",
                "Headphones",
                true
                ),
                // shirt2
                new Product(
                7,
                5,
                99.99,
                "A nicer MeeShirt",
                "https://m.media-amazon.com/images/I/B1DBWbloIpS._CLa%7C2140%2C2000%7C51snuhwZFIL.png%7C0%2C0%2C2140%2C2000%2B0.0%2C0.0%2C2140.0%2C2000.0_AC_SL1500_.png",
                "TeeShirt",
                true
                ),
                // mask
                new Product(
                8,
                20,
                12.5,
                "A reusable surgical mask",
                "https://media-cldnry.s-nbcnews.com/image/upload/newscms/2022_29/3512377/screen_shot_2021-10-13_at_2-00-29_pm.png",
                "Mask",
                true
                ),
                // hat1
                new Product(
                4,
                20,
                10.0,
                "A fancy cap for a fancy person",
                "https://d3o2e4jr3mxnm3.cloudfront.net/Rocket-Vintage-Chill-Cap_66374_1_lg.png",
                "Hat",
                true
                ),
                // coat1
                new Product(
                5,
                3,
                80.0,
                "A nice coat",
                "https://www.pngarts.com/files/3/Women-Jacket-PNG-High-Quality-Image.png",
                "Coat",
                true
                )
        ));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/product"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Check For Names of Products
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Headphones"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("TeeShirt"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("Mask"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].name").value("Hat"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[4].name").value("Coat")
                );
    }

    // Test Finding Product By ID
    // /api/product/{id}
    @Test
    public void testProductById() throws Exception {
        when(productService.findById(1)).thenReturn(Optional.of(
                // headphone1
                (new Product(
                        1,
                        10,
                        20.0,
                        "A nice pair of headphones",
                        "https://i.insider.com/54eb437f6bb3f7697f85da71?width=1000&format=jpeg&auto=webp",
                        "Headphones",
                        true
                ))
        ));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/product/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Headphones"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("A nice pair of headphones"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(20.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(10)
                );

        when(productService.findById(10)).thenReturn(Optional.of(
                // headphone1
                (new Product(
                        10,
                        3,
                        1.99,
                        "A niceish coat",
                        "https://media.bergdorfgoodman.com/f_auto,q_auto:good,ar_5:7,c_fill,dpr_1.0,w_720/01/bg_4370145_100664_m",
                        "Coat",
                        true
                ))
        ));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/product/10"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Coat"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("A niceish coat"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1.99))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(3)
                );
    }

    // Test Finding Product By Keyword
    // /api/product/search/{keyword}
    @Test
    public void testProductByKeyword() throws Exception {
        when(productService.findByKeyword("coat")).thenReturn(List.of(
                // coat1
                (new Product(
                        5,
                        3,
                        80.0,
                        "A nice coat",
                        "https://www.pngarts.com/files/3/Women-Jacket-PNG-High-Quality-Image.png",
                        "Coat",
                        true
                )),
                // coat2
                (new Product(
                        10,
                        3,
                        1.99,
                        "A niceish coat",
                        "https://media.bergdorfgoodman.com/f_auto,q_auto:good,ar_5:7,c_fill,dpr_1.0,w_720/01/bg_4370145_100664_m",
                        "Coat",
                        true
                ))
        ));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/product/search/coat"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Coat"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Coat"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("A nice coat"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description").value("A niceish coat"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(80.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(1.99))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].quantity").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].quantity").value(3)
                );
    }
}
