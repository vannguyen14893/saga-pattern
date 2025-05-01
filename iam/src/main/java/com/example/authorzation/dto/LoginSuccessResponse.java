package com.example.authorzation.dto;

import java.util.Map;
import java.util.Set;

public record LoginSuccessResponse(String accessToken, String refreshToken,
                                   long issuedAt, long expiresAt, String tokenType, Map<String, Object> additionalParameters,
                                   Set<String> scopes) {

}
