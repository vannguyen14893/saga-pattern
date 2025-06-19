package com.saga.admin.controller.errormessage;

import com.saga.admin.service.errormessage.GetListErrorMessageService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(GetListErrorMessageController.class)
class GetListErrorMessageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private GetListErrorMessageService getListErrorMessageService;

    @Test
    void getListErrorMessages_success() throws Exception {
        Mockito.when(getListErrorMessageService.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/error-message/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

