package org.demo.cucumber.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.demo.cucumber.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws Exception {
        // Optionally clear users if needed
    }

    @Test
    public void testUserManagementScenario() throws Exception {
        // 1. GET /user, expect 0 users
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        // 2. POST /user
        User user = new User();
        user.setName("John");
        user.setEmail("john@example.com");
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.email", is("john@example.com")));

        // 3. GET /user/email/{email}
        mockMvc.perform(get("/user/email/{email}", "john@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.email", is("john@example.com")));

        // 4. PUT /user (update)
        User updatedUser = new User();
        updatedUser.setName("JohnUpdate");
        updatedUser.setEmail("john@example.com");
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("JohnUpdate")))
                .andExpect(jsonPath("$.email", is("john@example.com")));

        // 5. DELETE /user/{email}
        mockMvc.perform(delete("/user/{email}", "john@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("JohnUpdate")))
                .andExpect(jsonPath("$.email", is("john@example.com")));
    }
}

