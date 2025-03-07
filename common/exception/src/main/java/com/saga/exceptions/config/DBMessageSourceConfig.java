package com.saga.exceptions.config;

import com.saga.exceptions.utils.Languages;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Objects;

@Component("messageSource")
public class DBMessageSourceConfig extends AbstractMessageSource {
    @Override
    protected MessageFormat resolveCode(String s, Locale locale) {
        return new MessageFormat(Objects.requireNonNullElse(Languages.messagesVn(s), "error"), locale);
    }
    public String getMessages(String key) {
        return getMessage(key, null, Locale.ENGLISH);
    }
}
