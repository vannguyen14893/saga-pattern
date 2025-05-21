package com.saga.product.exception;

import com.saga.exceptions.exceptions.CustomGlobalExceptionHandler;
import org.springframework.context.annotation.Configuration;

/**
 * Global exception handler for product-related exceptions.
 * This configuration class extends the CustomGlobalExceptionHandler to provide
 * specific exception handling for the product service.
 */
@Configuration
public class ProductException extends CustomGlobalExceptionHandler {

}
