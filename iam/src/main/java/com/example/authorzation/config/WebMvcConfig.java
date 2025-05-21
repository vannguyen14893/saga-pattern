package com.example.authorzation.config;

import com.saga.exceptions.config.DBMessageSourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for Spring MVC setup.
 * Provides resource handling configuration and custom validator setup.
 * Extends DBMessageSourceConfig for database-driven message source functionality.
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends DBMessageSourceConfig implements WebMvcConfigurer {
    /**
     * Configures static resource handling for the application.
     * Maps URL patterns to specific resource locations for images, static files, CSS, and JavaScript.
     *
     * @param registry The ResourceHandlerRegistry to configure resource handling
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");

    }

    /**
     * Provides a custom validator factory that uses database-driven message source.
     * Creates LocalValidatorFactoryBean configured with DBMessageSourceConfig.
     *
     * @return Configured LocalValidatorFactoryBean instance
     */
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        factory.setValidationMessageSource(new DBMessageSourceConfig());
        return factory;
    }
}
