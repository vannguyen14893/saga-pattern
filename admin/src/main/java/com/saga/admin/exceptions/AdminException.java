package com.saga.admin.exceptions;

import com.saga.exceptions.exceptions.CustomGlobalExceptionHandler;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for handling admin-specific exceptions in the application.
 * Extends the CustomGlobalExceptionHandler to provide centralized exception handling
 * for the admin module.
 */
@Configuration
public class AdminException extends CustomGlobalExceptionHandler {
}
