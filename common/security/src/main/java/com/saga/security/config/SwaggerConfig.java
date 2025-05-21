package com.saga.security.config;

import com.saga.dto.response.ResponseError;
import com.saga.security.dto.SwaggerConfigProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.*;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Configuration class for Swagger/OpenAPI documentation.
 * This class provides configuration for API documentation using Swagger/OpenAPI 3.0,
 * including custom response definitions, security schemes, and server information.
 */
@Configuration
public class SwaggerConfig {
    /**
     * Creates and configures SwaggerConfigProperties bean.
     *
     * @return SwaggerConfigProperties instance with default configuration
     */
    @Bean
    public SwaggerConfigProperties swaggerConfigProperties() {
        return new SwaggerConfigProperties();
    }


    /**
     * Customizes Swagger operations by adding standard API responses.
     * Adds common HTTP status codes (400, 401, 403, 404, 405, 415, 500) with corresponding error responses.
     *
     * @return OperationCustomizer instance configured with standard API responses
     */
    @Bean
    public OperationCustomizer operationCustomizer() {
        return (operation, handlerMethod) -> {
//            operation.getResponses().addApiResponse("1", new ApiResponse().
//                    description("Business exception").content(new Content()
//                            .addMediaType("application/json",
//                                    new MediaType()
//                                            .schema(new Schema<>()
//                                                    .$ref("#/components/schemas/ResponseError"))
//                                            .example(new ResponseError<>(UUID.randomUUID().toString(), "001", "Business exception")))));
            operation.getResponses().addApiResponse("400", new ApiResponse().
                    description("Handle method argument not valid").content(new Content()
                            .addMediaType("application/json",
                                    new MediaType()
                                            .schema(new Schema<>()
                                                    .$ref("#/components/schemas/ResponseError"))
                                            .example(new ResponseError<>(UUID.randomUUID().toString(), "400", "Handle method argument not valid")))));
            operation.getResponses().addApiResponse("401", new ApiResponse().
                    description("Unauthorized").content(new Content()
                            .addMediaType("application/json",
                                    new MediaType()
                                            .schema(new Schema<>()
                                                    .$ref("#/components/schemas/ResponseError"))
                                            .example(new ResponseError<>(UUID.randomUUID().toString(), "401", "Unauthorized")))));
            operation.getResponses().addApiResponse("403", new ApiResponse().
                    description("Forbidden").content(new Content()
                            .addMediaType("application/json",
                                    new MediaType()
                                            .schema(new Schema<>()
                                                    .$ref("#/components/schemas/ResponseError"))
                                            .example(new ResponseError<>(UUID.randomUUID().toString(), "403", "Forbidden")))));
            operation.getResponses().addApiResponse("404", new ApiResponse().
                    description("Resource not found").content(new Content()
                            .addMediaType("application/json",
                                    new MediaType()
                                            .schema(new Schema<>()
                                                    .$ref("#/components/schemas/ResponseError"))
                                            .example(new ResponseError<>(UUID.randomUUID().toString(), "404", "Resource not found")))));
            operation.getResponses().addApiResponse("405", new ApiResponse().
                    description("Method Not Allowed").content(new Content()
                            .addMediaType("application/json",
                                    new MediaType()
                                            .schema(new Schema<>()
                                                    .$ref("#/components/schemas/ResponseError"))
                                            .example(new ResponseError<>(UUID.randomUUID().toString(), "405", "Method Not Allowed")))));
            operation.getResponses().addApiResponse("415", new ApiResponse().
                    description("Unsupported Media Type").content(new Content()
                            .addMediaType("application/json",
                                    new MediaType()
                                            .schema(new Schema<>()
                                                    .$ref("#/components/schemas/ResponseError"))
                                            .example(new ResponseError<>(UUID.randomUUID().toString(), "415", "Unsupported Media Type")))));
            operation.getResponses().addApiResponse("500", new ApiResponse().
                    description("Server error").content(new Content()
                            .addMediaType("application/json",
                                    new MediaType()
                                            .schema(new Schema<>()
                                                    .$ref("#/components/schemas/ResponseError"))
                                            .example(new ResponseError<>(UUID.randomUUID().toString(), "500", "Server error")))));
            return operation;
        };
    }

    /**
     * Creates and configures the OpenAPI specification.
     * Configures API information, security schemes, servers, and common schema components.
     *
     * @return OpenAPI instance configured with application-specific settings
     */
    @Bean
    public OpenAPI customOpenAPI() {
        SecurityScheme.Type[] values = SecurityScheme.Type.values();
        SecurityScheme.Type type = null;
        for (SecurityScheme.Type value : values) {
            if (Objects.equals(value.toString(), swaggerConfigProperties().getSecurityType())) {
                type = value;
                break;
            }
        }
        OpenAPI openAPI = new OpenAPI()
                .info(new Info()
                        .title(swaggerConfigProperties().getTitle())
                        .version(swaggerConfigProperties().getVersion())
                        .description(swaggerConfigProperties().getDescription())
                        .termsOfService(swaggerConfigProperties().getTermsOfService())
                        .license(new License().name(swaggerConfigProperties().getLicenseName())
                                .url(swaggerConfigProperties().getLicenseUrl())))
                .components(new Components()
                        .addSchemas("ResponseError", new ObjectSchema()
                                .addProperty("status", new IntegerSchema().example(200))
                                .addProperty("message", new StringSchema().example("Operation error")))
                        .addSecuritySchemes(swaggerConfigProperties().getSecurityScheme(), new SecurityScheme()
                                .type(type)
                                .scheme(swaggerConfigProperties().getTokenType())
                                .bearerFormat(swaggerConfigProperties().getBearerFormat())))
                .addSecurityItem(new SecurityRequirement().addList(swaggerConfigProperties().getSecurityScheme()));
        List<Server> servers = new ArrayList<>();
        for (String serverInfo : swaggerConfigProperties().getListServer()) {
            String[] serverInfos = serverInfo.split(",");
            servers.add(new Server().url(serverInfos[0]).description(serverInfos[1]));
        }
        openAPI.servers(servers);
        return openAPI;
    }
}
