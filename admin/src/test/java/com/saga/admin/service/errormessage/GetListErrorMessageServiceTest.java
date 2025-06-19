package com.saga.admin.service.errormessage;

import com.saga.admin.dto.response.errormessage.ErrorMessageResponse;
import com.saga.admin.entity.ErrorMessage;
import com.saga.admin.repository.ErrorMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetListErrorMessageServiceTest {
    @Mock
    private ErrorMessageRepository errorMessageRepository;
    @Mock
    private ConvertErrorMessageResponseService convertErrorMessageResponseService;
    @InjectMocks
    private GetListErrorMessageService getListErrorMessageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_shouldReturnListOfErrorMessageResponse() {
        ErrorMessage errorMessage1 = new ErrorMessage();
        ErrorMessage errorMessage2 = new ErrorMessage();
        ErrorMessageResponse response1 = new ErrorMessageResponse(1L, "en", "Error 1");
        ErrorMessageResponse response2 = new ErrorMessageResponse(2L, "en", "Error 2");
        when(errorMessageRepository.findAll()).thenReturn(Arrays.asList(errorMessage1, errorMessage2));
        when(convertErrorMessageResponseService.convertToErrorMessageResponse(errorMessage1)).thenReturn(response1);
        when(convertErrorMessageResponseService.convertToErrorMessageResponse(errorMessage2)).thenReturn(response2);

        List<ErrorMessageResponse> result = getListErrorMessageService.findAll();
        assertThat(result).containsExactly(response1, response2);
        verify(errorMessageRepository, times(1)).findAll();
        verify(convertErrorMessageResponseService, times(1)).convertToErrorMessageResponse(errorMessage1);
        verify(convertErrorMessageResponseService, times(1)).convertToErrorMessageResponse(errorMessage2);
    }
}

