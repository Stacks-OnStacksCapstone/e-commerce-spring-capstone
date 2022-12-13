package com.revature;

import com.revature.dtos.Principal;
import com.revature.security.JWTConfig;
import com.revature.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetAuthToken {
    @Autowired
    private MockMvc mockMvc;

    public String token;

    public String getAuthToken() throws Exception {
        MvcResult result =
                mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"email\": \"testuser@gmail.com\",\n" +
                                "    \"password\": \"password\"\n" +
                                "}"))
                        .andExpect(status().isOk())
                        .andReturn();

        MockHttpServletRequest request = result.getRequest();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String token = generateToken(email, password);

        MockHttpServletResponse response = result.getResponse();
        response.addHeader("Authorization", token);

        return token;
    }

    public String generateToken(String email, String password) {
        TokenGenerator tokenGenerator = new TokenGenerator(new JWTConfig());
        return tokenGenerator.createToken(new Principal(1, email, true, true));
    }
}
