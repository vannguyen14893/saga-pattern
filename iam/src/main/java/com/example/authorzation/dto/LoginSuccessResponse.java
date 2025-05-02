package com.example.authorzation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Map;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record LoginSuccessResponse(String accessToken, String refreshToken, Long expiresAt, String tokenType,
                                   Map<String, Object> additionalParameters, Set<String> scopes, String otp) {

}
