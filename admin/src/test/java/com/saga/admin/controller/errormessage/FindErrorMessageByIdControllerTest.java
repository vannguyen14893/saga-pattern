package com.saga.admin.controller.errormessage;

import com.saga.admin.dto.response.errormessage.ErrorMessageResponse;
import com.saga.admin.service.errormessage.FindErrorMessageByIdService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FindErrorMessageByIdController.class)
class FindErrorMessageByIdControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private FindErrorMessageByIdService findErrorMessageByIdService;

    @Test
    void findErrorMessageById_success() throws Exception {
        Long id = 1L;
        ErrorMessageResponse response = new ErrorMessageResponse();
        Mockito.when(findErrorMessageByIdService.findById(id)).thenReturn(response);
        mockMvc.perform(get("/error-message/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}


