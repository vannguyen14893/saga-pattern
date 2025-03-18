package com.saga.email.config;

import com.saga.email.grpc.GetNotifyToCacheGrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import org.thymeleaf.templateresource.ITemplateResource;

import java.util.Locale;
import java.util.Map;

@Component
public class CachedTemplateResolver extends StringTemplateResolver {
    @Autowired
    private GetNotifyToCacheGrpcService getNotifyToCacheGrpcService;

    @Override
    protected ITemplateResource computeTemplateResource(IEngineConfiguration configuration, String ownerTemplate, String template, Map<String, Object> templateResolutionAttributes) {
        Locale language = LocaleContextHolder.getLocale();
        String content = getNotifyToCacheGrpcService.getTemplateNotify(template, "vi");
        return super.computeTemplateResource(configuration, ownerTemplate, content, templateResolutionAttributes);
    }
}
