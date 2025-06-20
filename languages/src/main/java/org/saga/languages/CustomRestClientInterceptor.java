package org.saga.languages;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.StreamClosedException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
public class CustomRestClientInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        // Pre-processing (before request is sent)
        logRequest(request, body);
        // Add custom headers
        request.getHeaders().add("X-Request-ID", UUID.randomUUID().toString());
        // Execute request
        ClientHttpResponse response = execution.execute(request, body);
        InputStream responseBody = response.getBody();
        // Post-processing (after response is received)
        logResponse(responseBody);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) {
        log.info("Request body: {}", new String(body, StandardCharsets.UTF_8));
    }

    private void logResponse(InputStream response) {
        try {
            byte[] bodyBytes = StreamUtils.copyToByteArray(response);
            log.info("Response body: {}", new String(bodyBytes, StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.info("Could not read response body: {}", e.getMessage());
        }
    }
}
