package com.saga.email.config;

import com.saga.email.grpc.GetNotifyToCacheGrpcService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import org.thymeleaf.templateresource.ITemplateResource;

import java.util.Map;

/**
 * Custom Thymeleaf template resolver that fetches template content from a cache service via gRPC.
 * Extends StringTemplateResolver to provide cached template resolution functionality.
 * This resolver intercepts template requests and retrieves the template content from a remote cache
 * before delegating to the standard string template resolution process.
 */
@Component
@RequiredArgsConstructor
public class CachedTemplateResolver extends StringTemplateResolver {
    private final GetNotifyToCacheGrpcService getNotifyToCacheGrpcService;

    /**
     * Computes the template resource by fetching template content from cache service.
     *
     * @param configuration                the engine configuration
     * @param ownerTemplate                the owner template name
     * @param template                     the template identifier
     * @param templateResolutionAttributes template resolution attributes
     * @return the template resource with content fetched from cache
     */
    @Override
    protected ITemplateResource computeTemplateResource(IEngineConfiguration configuration, String ownerTemplate, String template, Map<String, Object> templateResolutionAttributes) {
        String content = getNotifyToCacheGrpcService.getTemplateNotify(template, "vi");
        return super.computeTemplateResource(configuration, ownerTemplate, content, templateResolutionAttributes);
    }
}
