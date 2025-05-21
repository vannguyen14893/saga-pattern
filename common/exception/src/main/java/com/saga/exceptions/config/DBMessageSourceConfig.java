package com.saga.exceptions.config;

import com.saga.exceptions.utils.Languages;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * Configuration class for handling message source in the application.
 * Extends AbstractMessageSource to provide custom message resolution
 * from database or other sources based on locale settings.
 */
@Component("messageSource")
public class DBMessageSourceConfig extends AbstractMessageSource {
    /**
     * Resolves message codes to their corresponding MessageFormat.
     * Falls back to "error" if no message is found for the given code.
     *
     * @param s      the code to lookup up, such as 'calculator.noRateFound'
     * @param locale the Locale to use for message resolution
     * @return the MessageFormat for the message
     */
    @Override
    protected MessageFormat resolveCode(String s, Locale locale) {
        return new MessageFormat(Objects.requireNonNullElse(Languages.messagesVn(s), "error"), locale);
    }

    /**
     * Retrieves a message for the specified key using English locale.
     *
     * @param key the message key to lookup
     * @return the message in English locale
     */
    public String getMessages(String key) {
        return getMessage(key, null, Locale.ENGLISH);
    }
}
