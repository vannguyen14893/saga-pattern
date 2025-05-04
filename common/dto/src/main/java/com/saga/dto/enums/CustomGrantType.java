package com.saga.dto.enums;

import lombok.Getter;

public enum CustomGrantType {
    CUSTOM_PASSWORD_GRANT("custom_password"),
    CUSTOM_REFRESH_TOKEN_GRANT("custom_refresh_token"),
    CUSTOM_SMS_GRANT("custom_sms"),
    CUSTOM_EMAIL_GRANT("custom_email"),
    CUSTOM_REGIS_GRANT("custom_regis"),
    CUSTOM_GOOGLE_AUTH_GRANT("custom_google_auth");

    @Getter
    private final String value;

    CustomGrantType(String value) {
        this.value = value;
    }

}
