package com.saga.security.config;

import com.saga.security.dto.SwaggerConfigProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
public class SwaggerConfig {
    @Bean
    public SwaggerConfigProperties swaggerConfigProperties() {
        return new SwaggerConfigProperties();
    }

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
