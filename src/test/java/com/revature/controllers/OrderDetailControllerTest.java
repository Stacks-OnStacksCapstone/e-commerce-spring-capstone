package com.revature.controllers;

import com.revature.ECommerceApplication;
import com.revature.dtos.Principal;
import com.revature.models.User;
import com.revature.services.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = ECommerceApplication.class)

public class OrderDetailControllerTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    public String getToken() throws Exception {
        /*User user1 = new User(1,"testuser@gmail.com", "password", "Testerson", "Usertown",
                true, true,null);*/
        User user1 = userService.findUserById(1);
        Principal payload = new Principal(user1);
        String token = tokenService.generateToken(payload);
        return token;
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    //Should be 200 but is 400 instead!
    @Test

    public void testapiorderdetail() throws Exception {

        mockMvc.perform(post("/api/orderdetail")
                        .header("Authorization",getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                         .content("{\"productId\": 1,\n" +
                                 "  \"orderId\": 1,\n" +
                                 "  \"quantity\": 1 }")
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk());
        }


    @Test
    public void testdeleteapinonexistentorderdetailbyid() throws Exception {
        mockMvc.perform(delete("/api/orderdetail/987654321")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",getToken())
                        .content(""))
                .andDo(print())

                .andExpect(status().is4xxClientError());
            }


    @Test
    public void testdeleteexistentorderdetailbyid() throws Exception {
        mockMvc.perform(delete("/api/orderdetail/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",getToken())
                        .content(""))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testgetapiorderdetailbyid() throws Exception {
        mockMvc.perform(get("/api/orderdetail/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",getToken())
                        .content(""))
                .andDo(print())

                .andExpect(status().isOk());
            }
    @Test
    public void testgetapiorderdetailbyfakeid() throws Exception {
        mockMvc.perform(get("/api/orderdetail/987654321")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",getToken())
                        .content(""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testgetapiorderdetailbyorder() throws Exception {
        mockMvc.perform(get("/api/orderdetail/order/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",getToken())
                        .content(""))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testgetapiorderdetailbyorderResponseZero() throws Exception {

        mockMvc.perform(get("/api/orderdetail/order/4")

                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",getToken())
                        .content(""))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testgetapiorderdetailbyorderResponseUserOrderZero() throws Exception {
        mockMvc.perform(get("/api/orderdetail/order/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",getToken())
                        .content(""))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void testgetapiorderdetailbyfakeorder() throws Exception {
        mockMvc.perform(get("/api/orderdetail/order/987654321")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",getToken())
                        .content(""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testgetapiorderdetailbynorder() throws Exception {
        mockMvc.perform(get("/api/orderdetail/order/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",getToken())
                        .content(""))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void testgetapiorderdetailwithnoauth() throws Exception {
        mockMvc.perform(get("/api/orderdetail/order/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}



// -- Notes --
//Get Auth from postman!
//Cannot generate auth token using a real user or fake user, keeps returning an empty token string, even though the user exists.
// Why?
//having a hard time generating auth token
//String token = authService.generateAuthToken(muser1);
//Principal payload = new Principal(muser1);
//String token1 = tokenGenerator.createToken(payload);
//String token = tokenService.generateToken(payload);
//System.out.println(token1);
//String  authService.getUserByAuthToken("1");
// User user = userService.findUserById(id);
//Get a 404 response should be 200
// User muser1 = new User(1, "testuser@gmail.com","password", "Testerson", "Usertown",
//true, true, "");
//System.out.println(muser1);
//Get a 404 response should be 200
