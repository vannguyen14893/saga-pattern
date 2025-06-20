package org.saga.languages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.exceptions.exceptions.CustomGlobalExceptionHandler;
import com.saga.exceptions.exceptions.CustomResponseErrorHandler;
import com.saga.exceptions.exceptions.CustomServerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class LanguagesApplication extends CustomGlobalExceptionHandler {

    public static void main(String[] args) {
        BigDecimal min = new BigDecimal("2.00");
        BigDecimal max = new BigDecimal("6.00");
        boolean b = min.compareTo(max) == 0;
        System.out.println("result ---" + b);
        SpringApplication.run(LanguagesApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new CustomResponseErrorHandler());
        return restTemplate;
    }

    @Bean
    public RestClient restClient() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setReadTimeout(Duration.ofMinutes(1));
        requestFactory.setConnectTimeout(Duration.ofMinutes(1));
        requestFactory.setConnectionRequestTimeout(Duration.ofMinutes(1));

        return RestClient.builder()
                .requestFactory(requestFactory)
                .messageConverters(converters -> {
                    StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
                    stringConverter.setSupportedMediaTypes(Arrays.asList(
                            MediaType.APPLICATION_XML,
                            MediaType.TEXT_XML,
                            MediaType.ALL
                    ));
                    converters.removeIf(c -> c instanceof StringHttpMessageConverter);
                    converters.addFirst(stringConverter);
                })
                .defaultStatusHandler(new CustomResponseErrorHandler())
                .requestInterceptor(new CustomRestClientInterceptor())
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ApiAdapter apiAdapter() {
        return new ApiAdapter();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}
