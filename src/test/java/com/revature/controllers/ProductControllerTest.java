package com.revature.controllers;

import com.revature.ECommerceApplication;
import com.revature.services.AuthService;
import com.revature.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ECommerceApplication.class)
public class ProductControllerTest {
    @MockBean(name = "AuthService")
    private AuthService authService;

    @MockBean(name="ProductService")
    private ProductService productService;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        int[] orders = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] productIDs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] quantity = {10, 5, 20, 20, 3, 10, 5, 20, 1, 3};
        double[] price = {20.0, 45.0, 2.5, 10.0, 80.0, 120.0, 99.99, 12.5, 1000.0, 1.99};
        String[] descriptions = {
                "A nice pair of headphones",
                "A nice TeeShirt",
                "A reusable shopping bag",
                "A fancy cap for a fancy person",
                "A nice coat",
                "A beater set of headphones",
                "A nicer MeeShirt",
                "A reusable surgical mask",
                "A fancier hat for a fancier person",
                "A niceish coat"
        };
        String[] images = {
                "https://i.insider.com/54eb437f6bb3f7697f85da71?width=1000&format=jpeg&auto=webp",
                "https://d3o2e4jr3mxnm3.cloudfront.net/Mens-Jake-Guitar-Vintage-Crusher-Tee_68382_1_lg.png",
                "https://images.ctfassets.net/5gvckmvm9289/3BlDoZxSSjqAvv1jBJP7TH/65f9a95484117730ace42abf64e89572/Noissue-x-Creatsy-Tote-Bag-Mockup-Bundle-_4_-2.png",
                "https://d3o2e4jr3mxnm3.cloudfront.net/Rocket-Vintage-Chill-Cap_66374_1_lg.png",
                "https://www.pngarts.com/files/3/Women-Jacket-PNG-High-Quality-Image.png",
                "https://www.beatsbydre.com/content/dam/beats-support/global/images/product-hero/set-up-and-use-studio-wireless.jpg.large.2x.jpg",
                "https://m.media-amazon.com/images/I/B1DBWbloIpS._CLa%7C2140%2C2000%7C51snuhwZFIL.png%7C0%2C0%2C2140%2C2000%2B0.0%2C0.0%2C2140.0%2C2000.0_AC_SL1500_.png",
                "https://media-cldnry.s-nbcnews.com/image/upload/newscms/2022_29/3512377/screen_shot_2021-10-13_at_2-00-29_pm.png",
                "https://cdn.shopify.com/s/files/1/2624/7744/products/ReversibleRen-RedSilver-Leather-TopHat-AmericanHatMakers-Men-STUD_900x900.jpg?v=1668713281",
                "https://media.bergdorfgoodman.com/f_auto,q_auto:good,ar_5:7,c_fill,dpr_1.0,w_720/01/bg_4370145_100664_m"
        };
        String[] names = {
                "Headphones",
                "TeeShirt",
                "Shopping Bag",
                "Hat",
                "Coat",
                "Headphones",
                "TeeShirt",
                "Mask",
                "Hat",
                "Coat"
        };

        for(int order : orders) {
            this.mockMvc
                    .perform(get("/api/product"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    // Check For Names of Products
                    .andExpect(MockMvcResultMatchers.jsonPath("[" + order + "].id").value(productIDs[order]))
                    .andExpect(MockMvcResultMatchers.jsonPath("[" + order + "].quantity").value(quantity[order]))
                    .andExpect(MockMvcResultMatchers.jsonPath("[" + order + "].price").value(price[order]))
                    .andExpect(MockMvcResultMatchers.jsonPath("[" + order + "].description").value(descriptions[order]))
                    .andExpect(MockMvcResultMatchers.jsonPath("[" + order + "].image").value(images[order]))
                    .andExpect(MockMvcResultMatchers.jsonPath("[" + order + "].name").value(names[order]))
                    .andExpect(MockMvcResultMatchers.jsonPath("[" + order + "].active").value(true));
        }
    }

    @Test
    public void testProductById() throws Exception {
        int[] orders = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        String[] names = {
                "Headphones",
                "TeeShirt",
                "Shopping Bag",
                "Hat",
                "Coat",
                "Headphones",
                "TeeShirt",
                "Mask",
                "Hat",
                "Coat"
        };
        String[] descriptions = {
                "A nice pair of headphones",
                "A nice TeeShirt",
                "A reusable shopping bag",
                "A fancy cap for a fancy person",
                "A nice coat",
                "A beater set of headphones",
                "A nicer MeeShirt",
                "A reusable surgical mask",
                "A fancier hat for a fancier person",
                "A niceish coat"
        };

        for(int order : orders) {
            this.mockMvc
                    .perform(get("/api/product/{id}", order + 1))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath(".name").value(names[order]))
                    .andExpect(MockMvcResultMatchers.jsonPath(".description").value(descriptions[order]));
        }
    }

    @Test
    public void testProductByKeyword() throws Exception {
        String[][] products = {
                {"headphones", "Headphones", "A nice pair of headphones"},
                {"teeshirt", "TeeShirt", "A nice TeeShirt"},
                {"bag", "Shopping Bag", "A reusable shopping bag"},
                {"hat", "Hat", "A fancy cap for a fancy person"},
                {"coat", "Coat", "A nice coat"},
                {"mask", "Mask", "A reusable surgical mask"}
        };

        for(String[] product : products) {
            this.mockMvc
                    .perform(get("/api/product/search/{keyword}", product[0]))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("[0].name").value(product[1]))
                    .andExpect(MockMvcResultMatchers.jsonPath("[0].description").value(product[2]));
        }
    }

    // TODO: put product needs authentication to work
    @Test
    public void testPutProduct() throws Exception {
        this.mockMvc
                .perform(put("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{   \n" +
                                "    \"id\": 10,\n" +
                                "    \"quantity\": 1,\n" +
                                "    \"price\": 1000000.00,\n" +
                                "    \"description\": \"Unique Teapot\",\n" +
                                "    \"image\": \"https://cdn.shopify.com/static/sample-images/teapot.jpg\",\n" +
                                "    \"name\": \"Cup\",\n" +
                                "    \"active\": false\n" +
                                "}"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].id").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].quantity").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].price").value(1000000.00))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].description").value("Unique Teapot"))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].image").value("https://cdn.shopify.com/static/sample-images/teapot.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].name").value("Cup"))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].active").value(false));
    }

    // TODO: delete product needs authentication to work
    @Test
    public void testDeleteProduct() throws Exception {
        this.mockMvc
                .perform(delete("/api/product/{id}", 10))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].id").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].quantity").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].price").value(1.99))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].description").value("A niceish coat"))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].image").value("https://media.bergdorfgoodman.com/f_auto,q_auto:good,ar_5:7,c_fill,dpr_1.0,w_720/01/bg_4370145_100664_m"))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].name").value("Coat"))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].active").value(false));
    }

    // TODO: patch product needs authentication to work
    @Test
    public void testPatchProduct() throws Exception {
        this.mockMvc
                .perform(patch("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\n" +
                                "    {\n" +
                                "        \"id\": 3,\n" +
                                "        \"quantity\": 4\n" +
                                "    }\n" +
                                "  ]"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].quantity").value(16))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].price").value(2.5))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].description").value("A reusable shopping bag"))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].image").value("https://images.ctfassets.net/5gvckmvm9289/3BlDoZxSSjqAvv1jBJP7TH/65f9a95484117730ace42abf64e89572/Noissue-x-Creatsy-Tote-Bag-Mockup-Bundle-_4_-2.png"))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].name").value("Shopping Bag"))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].active").value(true));
    }
}
