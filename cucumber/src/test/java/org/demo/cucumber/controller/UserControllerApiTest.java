package org.demo.cucumber.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.demo.cucumber.entity.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static final String EMAIL = "john@example.com";

    @Test
    @Order(1)
    void testGetAllUsers_Empty() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @Order(2)
    void testCreateUser() throws Exception {
        User user = new User();
        user.setName("John");
        user.setEmail(EMAIL);
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.email", is(EMAIL)));
    }

    @Test
    @Order(3)
    void testGetUserByEmail() throws Exception {
        mockMvc.perform(get("/user/email/{email}", EMAIL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.email", is(EMAIL)));
    }

    @Test
    @Order(4)
    void testUpdateUser() throws Exception {
        User updatedUser = new User();
        updatedUser.setName("JohnUpdate");
        updatedUser.setEmail(EMAIL);
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("JohnUpdate")))
                .andExpect(jsonPath("$.email", is(EMAIL)));
    }

    @Test
    @Order(5)
    void testDeleteUserByEmail() throws Exception {
        mockMvc.perform(delete("/user/{email}", EMAIL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("JohnUpdate")))
                .andExpect(jsonPath("$.email", is(EMAIL)));
    }
}

