package com.saga.admin.controller.errormessage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.admin.dto.request.errormessage.CreateErrorMessageRequest;
import com.saga.admin.dto.response.errormessage.ErrorMessageResponse;
import com.saga.admin.service.errormessage.CreateErrorMessageService;
import com.saga.dto.response.ResponseSuccess;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CreateErrorMessageController.class)
class CreateErrorMessageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private CreateErrorMessageService createErrorMessageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createErrorMessage_success() throws Exception {
        CreateErrorMessageRequest request = new CreateErrorMessageRequest();
        // Set fields as needed for your DTO
        ErrorMessageResponse response = new ErrorMessageResponse();
        // Set fields as needed for your DTO
        Mockito.when(createErrorMessageService.create(Mockito.any())).thenReturn(response);

        mockMvc.perform(post("/error-message")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists());
    }
}

