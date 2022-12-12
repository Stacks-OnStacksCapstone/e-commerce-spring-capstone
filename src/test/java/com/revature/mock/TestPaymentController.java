package com.revature.mock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.PaymentController;
import com.revature.controllers.ProductController;
import com.revature.dtos.EditPaymentRequest;
import com.revature.dtos.PaymentResponse;
import com.revature.models.Payment;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.PaymentService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers= PaymentController.class)
public class TestPaymentController {

    @MockBean
    private PaymentService paymentservice;

    @MockBean
    private AuthService authservice;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get All Payments Of User - /api/payment/")
    public void shouldListAllProducts() throws Exception {

        //User Object
        String jsonString2 = "{\"id\":\"1\",\"email\":\"test@test.com\",\"firstName\":\"first\",\"lastName\":\"last\",\"active\":\"true\",\"admin\":\"true\"}";
        ObjectMapper objectMapper2 = new ObjectMapper();
        User user = objectMapper2.readValue(jsonString2, User.class);
        System.out.println(user);

        //Payment Response List Object
        String jsonString3 = "{\"id\":\"1\",\"ccv\":\"333\",\"expDate\":\"2022-12-09T22:17:27.651Z\",\"cardNumber\":\"333344445555666\",\"userEmail\":\"test@example.com\"}";
        ObjectMapper objectMapper3 = new ObjectMapper();
        PaymentResponse pr = objectMapper3.readValue(jsonString3, PaymentResponse.class);
        List<PaymentResponse> prList = new ArrayList();
        prList.add(pr);

        //Payment Response List Object as String
        ObjectMapper objectMapper4 = new ObjectMapper();
        String json = objectMapper4.writeValueAsString(pr);


        System.out.println(user);

        when(authservice.getUserByAuthToken(anyString())).thenReturn(user);
        when(paymentservice.findAllByUser(1)).thenReturn(prList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/payment")
                .header("Authorization",""))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Delete Payment - /api/payment/")
    public void shouldDeletePayment() throws Exception {

        //User Object
        String jsonString2 = "{\"id\":\"1\",\"email\":\"test@test.com\",\"firstName\":\"first\",\"lastName\":\"last\",\"active\":\"true\",\"admin\":\"true\"}";
        ObjectMapper objectMapper2 = new ObjectMapper();
        User user = objectMapper2.readValue(jsonString2, User.class);
        System.out.println(user);

        //Payment Response List Object
        String jsonString3 = "{\"id\":\"1\",\"ccv\":\"333\",\"expDate\":\"2022-12-09T22:17:27.651Z\",\"cardNumber\":\"333344445555666\",\"userEmail\":\"test@example.com\"}";
        ObjectMapper objectMapper3 = new ObjectMapper();
        PaymentResponse pr = objectMapper3.readValue(jsonString3, PaymentResponse.class);
        System.out.println(pr);

        when(authservice.getUserByAuthToken(anyString())).thenReturn(user);
        when(paymentservice.deletePayment("1", user)).thenReturn(pr);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/payment/?paymentId=1")
                .header("Authorization",""))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(MockMvcResultMatchers.content().string("Payment 1 was deleted"))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Update Payment - /api/payment/")
    public void shouldUpdatePayment() throws Exception {

        //User Object
        String jsonString2 = "{\"id\":\"1\",\"email\":\"test@test.com\",\"firstName\":\"first\",\"lastName\":\"last\",\"active\":\"true\",\"admin\":\"true\"}";
        ObjectMapper objectMapper2 = new ObjectMapper();
        User user = objectMapper2.readValue(jsonString2, User.class);
        System.out.println(user);

        //Payment Response List Object
        String jsonString3 = "{\"paymentId\":\"5\",\"cardType\":\"Visa\",\"id\":\"1\",\"ccv\":\"333\",\"expDate\":\"2022-12-09T22:17:27.651Z\",\"cardNumber\":\"333344445555666\",\"userEmail\":\"test@example.com\"}";
        ObjectMapper objectMapper3 = new ObjectMapper();
        objectMapper3.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        EditPaymentRequest epr = objectMapper3.readValue(jsonString3, EditPaymentRequest.class);
        System.out.println(epr);
        PaymentResponse pr = objectMapper3.readValue(jsonString3, PaymentResponse.class);
        System.out.println(pr);

        when(authservice.getUserByAuthToken(anyString())).thenReturn(user);
        when(paymentservice.updatePayment(any(), any())).thenReturn(pr);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/payment/")
                        .header("Authorization","")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"id\":\"fdsasf\",\"ccv\":\"333\",\"expDate\":\"2022-12-12T19:02:52.720Z\",\"cardNumber\":\"333344445555\",\"userEmail\":\"test@example.com\"}"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Add Payment - /api/payment/")
    public void shouldAddPayment() throws Exception {

        //User Object
        String jsonString2 = "{\"id\":\"1\",\"email\":\"test@test.com\",\"firstName\":\"first\",\"lastName\":\"last\",\"active\":\"true\",\"admin\":\"true\"}";
        ObjectMapper objectMapper2 = new ObjectMapper();
        User user = objectMapper2.readValue(jsonString2, User.class);
        System.out.println(user);

        //Payment Response List Object
        String jsonString3 = "{\"paymentId\":\"5\",\"cardType\":\"Visa\",\"id\":\"1\",\"ccv\":\"333\",\"expDate\":\"2022-12-09T22:17:27.651Z\",\"cardNumber\":\"333344445555666\",\"userEmail\":\"test@example.com\"}";
        ObjectMapper objectMapper3 = new ObjectMapper();
        objectMapper3.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        EditPaymentRequest epr = objectMapper3.readValue(jsonString3, EditPaymentRequest.class);
        System.out.println(epr);
        PaymentResponse pr = objectMapper3.readValue(jsonString3, PaymentResponse.class);
        System.out.println(pr);

        when(authservice.getUserByAuthToken(anyString())).thenReturn(user);
        when(paymentservice.createPayment(any(), any())).thenReturn(pr);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/payment/")
                        .header("Authorization","")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"id\":\"fdsasf\",\"ccv\":\"333\",\"expDate\":\"2022-12-12T19:02:52.720Z\",\"cardNumber\":\"333344445555\",\"userEmail\":\"test@example.com\"}"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();
    }

}