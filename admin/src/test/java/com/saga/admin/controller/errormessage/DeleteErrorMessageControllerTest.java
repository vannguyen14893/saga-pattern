package com.saga.admin.controller.errormessage;

import com.saga.admin.service.errormessage.DeleteErrorMessageService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeleteErrorMessageController.class)
class DeleteErrorMessageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private DeleteErrorMessageService deleteErrorMessageService;

    @Test
    void deleteErrorMessage_success() throws Exception {
        Long id = 1L;
        Mockito.when(deleteErrorMessageService.delete(id)).thenReturn(id);
        mockMvc.perform(delete("/error-message/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

