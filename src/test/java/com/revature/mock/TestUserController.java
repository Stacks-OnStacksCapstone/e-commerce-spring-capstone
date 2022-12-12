package com.revature.mock;

import com.revature.controllers.UserController;
import com.revature.dtos.RegisterRequest;
import com.revature.dtos.UpdateUserRequest;
import com.revature.dtos.UserResponse;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import com.revature.services.AuthService;
import com.revature.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers= UserController.class)
public class TestUserController {

    @MockBean
    private UserService userService;
    @MockBean
    private AuthService authService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Add user - /user")
    public void addUser() throws Exception {
        User muser = new User(1, "fake@email.com", "password", "Fake", "Name", false, true, "fjidaop3898awe8f");
        when(userService.registerUser(new RegisterRequest("fake@email.com", "password", "Fake", "Name")))
                .thenReturn(new UserResponse(muser));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"id\":\"1\",\"email\":\"fake@email.com\",\"password\":\"password\",\"firstName\":\"Fake\",\"lastName\":\"Name\",\"isAdmin\":\"false\",\"isActive\":\"true\"}"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$.userId").value("1"))
                .andExpect(jsonPath("$.email").value("fake@email.com"))
                .andExpect(jsonPath("$.firstName").value("Fake"))
                .andExpect(jsonPath("$.lastName").value("Name"))
                .andExpect(jsonPath("$.admin").value(false))
                .andExpect(jsonPath("$.active").value(true))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Get user - /user")
    public void getUser() throws Exception {
        User muser = new User(1, "fake@email.com", "password", "Fake", "Name", false, true, "fjidaop3898awe8f");
        when(authService.getUserByAuthToken(anyString())).thenReturn(muser);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user")
                        .header("Authorization",""))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.userId").value("1"))
                .andExpect(jsonPath("$.email").value("fake@email.com"))
                .andExpect(jsonPath("$.firstName").value("Fake"))
                .andExpect(jsonPath("$.lastName").value("Name"))
                .andExpect(jsonPath("$.admin").value(false))
                .andExpect(jsonPath("$.active").value(true))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Update user - /user")
    public void updateUser() throws Exception {
        User muser = new User(1, "fake@email.com", "password", "Fake", "LastName", false, true, "fjidaop3898awe8f");
        userService.update(new UpdateUserRequest("Fake", "Name", "fake@email.com", "password"), muser);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"id\":\"1\",\"email\":\"fake@email.com\",\"password\":\"password\",\"firstName\":\"Fake\",\"lastName\":\"Name\",\"isAdmin\":\"false\",\"isActive\":\"true\"}"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(content().string("The user account is successfully updated!"))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("User deactivates account = /user/deactivate")
    public void deactivateUser()throws Exception {
        User muser = new User(1, "fake@email.com", "password", "Fake", "Name", false, true, "fjidaop3898awe8f");
        userService.deactivate(muser);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/user/deactivate"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(content().string("The user account is successfully deactivated!"))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Admin deactivates account = /user/deactivateUser")
    public void deactivateUserAdmin()throws Exception {
        User muser = new User(1, "fake@email.com", "password", "Fake", "Name", false, true, "fjidaop3898awe8f");
        userService.deactivate(muser);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/user/deactivateUser"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(content().string("The user account is successfully deactivated!"))
                .andDo(print())
                .andReturn();
    }

}
