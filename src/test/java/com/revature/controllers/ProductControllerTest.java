package com.revature.controllers;

import com.revature.ECommerceApplication;
import com.revature.dtos.Principal;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.ProductService;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void testPatchProductPositive() throws Exception {
        this.mockMvc
                .perform(patch("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\n" +
                                "    {\n" +
                                "        \"id\": 3,\n" +
                                "        \"quantity\": 4\n" +
                                "    }\n" +
                                "  ]")
                        .header("Authorization", getToken()))
                
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath(".quantity").value(16));
    }

    @Test
    public void testPatchProductNegative1() throws Exception {
        this.mockMvc
                .perform(patch("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\n" +
                                "    {\n" +
                                "        \"id\": 9999,\n" +
                                "        \"quantity\": 9999\n" +
                                "    }\n" +
                                "  ]")
                        .header("Authorization", getToken()))
                
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testPatchProductNegative2() throws Exception {
        this.mockMvc
                .perform(patch("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\n" +
                                "    {\n" +
                                "        \"id\": 3,\n" +
                                "        \"quantity\": 9999\n" +
                                "    }\n" +
                                "  ]")
                        .header("Authorization", getToken()))
                
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testPutProductPositive() throws Exception {
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
                                "}")
                        .header("Authorization", getToken()))
                
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".id").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath(".quantity").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".price").value(1000000.00))
                .andExpect(MockMvcResultMatchers.jsonPath(".description").value("Unique Teapot"))
                .andExpect(MockMvcResultMatchers.jsonPath(".image").value("https://cdn.shopify.com/static/sample-images/teapot.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath(".name").value("Cup"))
                .andExpect(MockMvcResultMatchers.jsonPath(".active").value(false));
    }

    @Test
    public void testPutProductNegative() throws Exception {
        this.mockMvc
                .perform(put("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{   \n" +
                                "    \"id\": 0,\n" +
                                "    \"quantity\": 0,\n" +
                                "    \"price\": 0,\n" +
                                "    \"description\": \"Air\",\n" +
                                "    \"image\": \"https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.swXJc8G6YJMcaO692mCoVAHaC1%26pid%3DApi&f=1&ipt=1e48299a2bf5e222bde47be1c36e0fedd2ed818df68879bedf58acc9defbc89e&ipo=images\",\n" +
                                "    \"name\": \"Air\",\n" +
                                "    \"active\": false\n" +
                                "}")
                        .header("Authorization", getToken()))
                
                .andExpect(MockMvcResultMatchers.status().isNotFound());
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
                    
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("[0].name").value(product[1]))
                    .andExpect(MockMvcResultMatchers.jsonPath("[0].description").value(product[2]));
        }
    }

    @Test
    public void testDeleteProductPositive() throws Exception {
        this.mockMvc
                .perform(delete("/api/product/{id}", 9)
                        .header("Authorization", getToken()))
                
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".id").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath(".quantity").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".price").value(1000.0))
                .andExpect(MockMvcResultMatchers.jsonPath(".description").value("A fancier hat for a fancier person"))
                .andExpect(MockMvcResultMatchers.jsonPath(".image").value("https://cdn.shopify.com/s/files/1/2624/7744/products/ReversibleRen-RedSilver-Leather-TopHat-AmericanHatMakers-Men-STUD_900x900.jpg?v=1668713281"))
                .andExpect(MockMvcResultMatchers.jsonPath(".name").value("Hat"))
                .andExpect(MockMvcResultMatchers.jsonPath(".active").value(false));
    }

    @Test
    public void testDeleteProductNegative() throws Exception {
        this.mockMvc
                .perform(delete("/api/product/{id}", 9999)
                        .header("Authorization", getToken()))
                
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetAllProducts() throws Exception {
        int[] orders = {0, 1, 2, 3, 4, 5, 6, 7};
        int[] productIDs = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] quantity = {10, 5, 16, 20, 3, 10, 5, 20};
        double[] price = {20.0, 45.0, 2.5, 10.0, 80.0, 120.0, 99.99, 12.5};
        String[] descriptions = {
                "A nice pair of headphones",
                "A nice TeeShirt",
                "A reusable shopping bag",
                "A fancy cap for a fancy person",
                "A nice coat",
                "A beater set of headphones",
                "A nicer MeeShirt",
                "A reusable surgical mask",
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
        };

        for(int order : orders) {
            this.mockMvc
                    .perform(get("/api/product"))
                    
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
    public void testProductByIdPositive() throws Exception {
        int[] orders = {0, 1, 2, 3, 4, 5, 6, 7};
        String[] names = {
                "Headphones",
                "TeeShirt",
                "Shopping Bag",
                "Hat",
                "Coat",
                "Headphones",
                "TeeShirt",
                "Mask",
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
        };

        for(int order : orders) {
            this.mockMvc
                    .perform(get("/api/product/{id}", order + 1))
                    
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath(".name").value(names[order]))
                    .andExpect(MockMvcResultMatchers.jsonPath(".description").value(descriptions[order]));
        }
    }

    @Test
    public void testProductByIdNegative() throws Exception {
        this.mockMvc
                .perform(get("/api/product/{id}", 100))
                
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
