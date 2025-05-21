package com.saga.security.config;

import com.saga.dto.enums.CustomGrantType;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Custom validator for OAuth2 JWT access tokens.
 * Performs validation of essential token claims and properties including:
 * - Issuer validation
 * - Token expiration
 * - Token type verification
 * - Required claims presence (user_id)
 * - Grant type validation
 * - Optional IP address validation
 */
public class CustomAccessTokenValidator implements OAuth2TokenValidator<Jwt> {
    /**
     * Validates the JWT token against multiple criteria.
     *
     * @param jwt The JWT token to validate
     * @return OAuth2TokenValidatorResult indicating success or containing validation errors
     */
    @Override
    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        List<OAuth2Error> errors = new ArrayList<>();
        validateIssuer(jwt, errors);
        validateExpiration(jwt, errors);
        validateTokenType(jwt, errors);

        // Validate required claims
        if (!jwt.hasClaim("user_id")) {
            errors.add(new OAuth2Error(
                    "invalid_token",
                    "Required claim 'user_id' is missing",
                    null
            ));
        }
        List<String> grantTypes = new ArrayList<>(Arrays.asList(CustomGrantType.CUSTOM_SMS_GRANT.getValue(), CustomGrantType.CUSTOM_EMAIL_GRANT.getValue(),
                CustomGrantType.CUSTOM_REGIS_GRANT.getValue(), CustomGrantType.CUSTOM_GOOGLE_AUTH_GRANT.getValue()));
        String grantType = jwt.getClaimAsString("grant_type");
        if (!grantTypes.contains(grantType)) {
            errors.add(new OAuth2Error(
                    "invalid_token",
                    "Grant type not supported",
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

    /**
     * Validates the token's expiration time.
     * Checks if expiration claim exists and if the token hasn't expired.
     *
     * @param jwt    The JWT token to validate
     * @param errors List to collect validation errors
     */
    private void validateExpiration(Jwt jwt, List<OAuth2Error> errors) {
        if (jwt.getExpiresAt() == null) {
            errors.add(new CustomOAuth2Error(
                    "missing_expiration",
                    "The token must have an expiration time",
                    null
            ));
        } else if (jwt.getExpiresAt().isBefore(Instant.now())) {
            errors.add(new CustomOAuth2Error(
                    "token_expired",
                    "The token has expired",
                    Map.of("expired_at", jwt.getExpiresAt().toString())
            ));
        }
    }

    /**
     * Validates the token's issuer.
     * Ensures the token was issued by an authorized party.
     *
     * @param jwt    The JWT token to validate
     * @param errors List to collect validation errors
     */
    private void validateIssuer(Jwt jwt, List<OAuth2Error> errors) {
        if (!"http://localhost:8088".equals(jwt.getClaimAsString("iss"))) {
            errors.add(new CustomOAuth2Error(
                    "invalid_issuer",
                    "The token was issued by an unauthorized party",
                    Map.of("received_issuer", jwt.getIssuer().toString())
            ));
        }
    }

    /**
     * Validates the token type claim.
     * Ensures the token is of type "Bearer".
     *
     * @param jwt    The JWT token to validate
     * @param errors List to collect validation errors
     */
    private void validateTokenType(Jwt jwt, List<OAuth2Error> errors) {
        String tokenType = jwt.getClaim("token_type");
        if (!"Bearer".equals(tokenType)) {
            errors.add(new CustomOAuth2Error(
                    "invalid_token_type",
                    "Only Bearer tokens are accepted",
                    Map.of("token_type", tokenType != null ? tokenType : "null")
            ));
        }
    }

    /**
     * Validates IP address format using regex pattern.
     * Accepts IPv4 addresses in standard dot-decimal notation.
     *
     * @param ip The IP address string to validate
     * @return true if the IP address is valid, false otherwise
     */
    private boolean isValidIp(String ip) {
        // Implement IP validation logic
        return ip != null && ip.matches("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$");
    }
}
