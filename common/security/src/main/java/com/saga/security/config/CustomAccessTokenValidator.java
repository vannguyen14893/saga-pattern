package com.saga.security.config;


import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.List;

public class CustomAccessTokenValidator implements OAuth2TokenValidator<Jwt> {
    @Override
    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        List<OAuth2Error> errors = new ArrayList<>();

        // Validate required claims
        if (!jwt.hasClaim("user_id")) {
            errors.add(new OAuth2Error(
                    "invalid_token",
                    "Required claim 'user_id' is missing",
                    null
            ));
        }
        // Validate IP address if present
        if (jwt.hasClaim("ip_address")) {
            String ip = jwt.getClaimAsString("ip_address");
            if (!isValidIp(ip)) {
                errors.add(new OAuth2Error(
                        "invalid_token",
                        "Invalid IP address in token",
                        null
                ));
            }
        }
        return errors.isEmpty() ?
                OAuth2TokenValidatorResult.success() :
                OAuth2TokenValidatorResult.failure(errors);
    }

    private boolean isValidIp(String ip) {
        // Implement IP validation logic
        return ip != null && ip.matches("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$");
    }
}
