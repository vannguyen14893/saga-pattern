package com.saga.exceptions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Custom implementation of {@link ResponseErrorHandler} to process HTTP error responses.
 * Throws {@link BusinessExceptionHandler} for 400 (Bad Request) and
 * {@link CustomServerException} for 500 (Internal Server Error), including the response body.
 * For other error statuses, delegates to the default error handling.
 */
public class CustomResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            String responseBody = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
            throw new BusinessExceptionHandler("Bad request occurred: " + responseBody);
        }
        if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            String responseBody = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
            throw new CustomServerException("Server error occurred: " + responseBody);
        }
        return response.getStatusCode().isError();
    }
}
