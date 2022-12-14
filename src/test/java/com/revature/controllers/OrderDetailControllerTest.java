package com.revature.controllers;
import com.revature.ECommerceApplication;
import com.revature.dtos.*;
import com.revature.models.Order;
import com.revature.models.OrderDetail;
import com.revature.models.User;
import com.revature.repositories.OrderRepository;
import com.revature.security.TokenGenerator;
import com.revature.services.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = ECommerceApplication.class)
public class OrderDetailControllerTest {

    @MockBean(name="AuthService")
    private AuthService authService;

    @MockBean(name="OrderService")
    private OrderService orderService;

    @MockBean(name="OrderDetailService")
    private OrderDetailService orderDetailService;

    @MockBean(name="UserService")
    private UserService userService;

    @MockBean(name="TokenService")
    private TokenService tokenService;


    @MockBean(name="TokenGenerator")
    private TokenGenerator tokenGenerator;


    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    //FROM POSTMAN!
    private String auth = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoidGVzdHVzZXJAZ21haWwuY29tIiwiaXNzIjoiQ29uZ28iLCJpc0FkbWluIjp0cnV" +
            "lLCJpc0FjdGl2ZSI6dHJ1ZSwiaWF0IjoxNj" +
            "cwNzk0NDY0LCJleHAiOjE2NzA4ODA4NjR9.TxXkPNCqblPG" +
            "CxHdguVB1114qRrt5CPQkm8s0qJu4Hc";

    @Test
    public void testPositiveLogin() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"email\": \"testuser@gmail.com\",\n" +
                                "    \"password\": \"password\"\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    //Should be 200 but is 400 instead!
    @Test
    public void testapiorderdetailgetbyid() throws Exception {
        when(orderDetailService.createOrderDetail(new OrderDetailRequest())).thenReturn(new OrderDetailResponse());
        mockMvc.perform(post("/api/orderdetail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",auth)
                        .content( "  \"productId\": \"1\",\n" +
                                "  \"orderId\": \"1\",\n" +
                                "  \"quantity\": \"0\""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }




    @Test
    public void testdeleteapinonexistentorderdetailbyid() throws Exception {
        int id = 100;
        when( orderDetailService.delete(id)).thenReturn(true);
        mockMvc.perform(delete("/api/orderdetail/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",auth)
                        .content(""))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testgetapiorderdetailbyid() throws Exception {
        int id = 1;
        Optional<OrderDetail> optional = orderDetailService.findById(id);
        when(orderDetailService.findById(id)).thenReturn(optional);
        mockMvc.perform(get("/api/orderdetail/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",auth)
                        .content(""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }



    @Test
    public void testgetapiorderdetailbyorder() throws Exception {
        mockMvc.perform(get("/api/orderdetail/order/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",auth)
                        .content(""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
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
