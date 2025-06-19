package org.saga.languages;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiAdapter {
    @Autowired

    private RestTemplate restTemplate;

    public String adapter(ConfigApi configApi) {
        ResponseEntity<String> response = restTemplate.exchange(
                configApi.url,
                HttpMethod.valueOf(configApi.method),
                buildRequestEntity(configApi.request != null ? transform(configApi.request, configApi.requestTemplate, configApi.type) : null, configApi.headers),
                String.class,
                configApi.params
        );
        return transform(response.getBody(), configApi.responseTemplate, configApi.type);
    }

    private HttpEntity<Object> buildRequestEntity(String request, Map<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        headers.forEach(httpHeaders::add);
        return new HttpEntity<>(request, httpHeaders);
    }

    private String transform(String input, String template, String type) {
        List<Object> specs = JsonUtils.jsonToList(template);
        Chainr chainr = Chainr.fromSpec(specs);
        // Parse the input JSON
        Object inputJsonConvert;
        switch (type) {
            case "json":
                inputJsonConvert = JsonUtils.jsonToObject(input);
                break;
            case "xml":
                XmlMapper xmlMapper = new XmlMapper();
                try {
                    JsonNode jsonNode = xmlMapper.readTree(input);
                    String jsonString = JsonUtils.toJsonString(jsonNode);
                    inputJsonConvert = JsonUtils.jsonToObject(jsonString);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Error parsing XML to JSON: " + e.getMessage(), e);
                }
                break;
            default:
                throw new IllegalArgumentException("Unsupported type: " + type);
        }
        // Transform the input JSON
        Object transformedOutput = chainr.transform(inputJsonConvert);
        return JsonUtils.toJsonString(transformedOutput);
    }

    @Data
    public static class ConfigApi {
        private String url;
        private String method;
        private Map<String, Object> params = new HashMap<>();
        private String responseTemplate;
        private String requestTemplate;
        private Map<String, String> headers;
        private String type;
        private String request;
    }
}
