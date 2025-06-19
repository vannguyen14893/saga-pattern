package com.saga.email.config;

import com.saga.email.grpc.GetNotifyToCacheGrpcService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * Configuration class for Spring Mail and Thymeleaf template engine setup.
 * Provides beans necessary for email template processing and rendering.
 */
@Configuration
@RequiredArgsConstructor
public class SpringMailConfig {
    private final GetNotifyToCacheGrpcService getNotifyToCacheGrpcService;

    /**
     * Creates and configures a Thymeleaf template engine bean for processing HTML email templates.
     * Uses CachedTemplateResolver for template resolution with HTML mode and caching disabled.
     *
     * @return configured TemplateEngine instance
     */
    @Bean
    public TemplateEngine templateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();
        CachedTemplateResolver templateResolver = new CachedTemplateResolver(getNotifyToCacheGrpcService);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCheckExistence(true);
        templateResolver.setCacheable(false);
        templateEngine.addTemplateResolver(templateResolver);
        return templateEngine;
    }

}
