package com.example.authorzation.config;

import com.example.authorzation.exceptions.AuthenticationExceptionHandler;
import com.example.authorzation.exceptions.BusinessExceptionHandler;
import com.example.authorzation.exceptions.UserDetailNotFound;
import com.saga.database.config.DatabaseConfig;
import com.saga.dto.response.ResponseError;
import com.saga.exceptions.config.DBMessageSourceConfig;
import com.saga.exceptions.exceptions.CustomGlobalExceptionHandler;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@Slf4j
public class CommonConfig extends CustomGlobalExceptionHandler implements DatabaseConfig {

    @ExceptionHandler({AuthenticationExceptionHandler.class})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "403",
                    description = "Account status exception",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class)
                    )
            )
    })
    public ResponseEntity<ResponseError<String>> accountStatusException(final AuthenticationExceptionHandler ex) {
        log.info(ex.getClass().getName());
        return execute(HttpStatus.UNAUTHORIZED.value(), String.format(new DBMessageSourceConfig().getMessages("403"), ex.getMessage()));
    }

    @ExceptionHandler({UserDetailNotFound.class})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Resource not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class)
                    )
            )
    })
    protected ResponseEntity<ResponseError<String>> userNotFoundExceptionHandler(final UserDetailNotFound ex) {
        log.info(ex.getClass().getName());
        return execute(404, String.format(new DBMessageSourceConfig().getMessages("404"), ex.getMessage()));
    }

    @ExceptionHandler({BusinessExceptionHandler.class})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "400",
                    description = "Business exception",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class)
                    )
            )
    })
    protected ResponseEntity<ResponseError<String>> businessExceptionHandler(final BusinessExceptionHandler ex) {
        log.info(ex.getClass().getName());
        return execute(400, new DBMessageSourceConfig().getMessages(ex.getMessage()));
    }
}
