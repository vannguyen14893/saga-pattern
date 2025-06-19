package com.saga.admin.controller.errormessage;

import com.saga.admin.service.errormessage.UpdateErrorMessageService;
import com.saga.admin.dto.request.errormessage.UpdateErrorMessageRequest;
import com.saga.admin.dto.response.errormessage.ErrorMessageResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UpdateErrorMessageController.class)
class UpdateErrorMessageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UpdateErrorMessageService updateErrorMessageService;

    @Test
    void updateErrorMessage_success() throws Exception {
        UpdateErrorMessageRequest request = new UpdateErrorMessageRequest();
        ErrorMessageResponse response = new ErrorMessageResponse();
        Mockito.when(updateErrorMessageService.update(Mockito.any())).thenReturn(response);
        mockMvc.perform(put("/error-message")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")) // provide valid JSON
                .andExpect(status().isOk());
    }
}

