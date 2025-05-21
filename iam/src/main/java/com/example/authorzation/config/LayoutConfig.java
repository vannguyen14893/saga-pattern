package com.example.authorzation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * Configuration class for template resolution setup.
 * Provides beans for HTML template processing using Thymeleaf.
 */
@Configuration
public class LayoutConfig {
    /**
     * Creates a ClassLoaderTemplateResolver bean configured for HTML templates.
     * Sets up template resolution from the classpath with UTF-8 encoding.
     *
     * @return ClassLoaderTemplateResolver configured for HTML template processing
     */
    @Bean
    public ClassLoaderTemplateResolver emailTemplateResolver() {
        ClassLoaderTemplateResolver pdfTemplateResolver = new ClassLoaderTemplateResolver();
        pdfTemplateResolver.setPrefix("templates/");
        pdfTemplateResolver.setSuffix(".html");
        pdfTemplateResolver.setTemplateMode("HTML");
        pdfTemplateResolver.setCacheable(false);
        pdfTemplateResolver.setCharacterEncoding("UTF-8");
        return pdfTemplateResolver;
    }

}
