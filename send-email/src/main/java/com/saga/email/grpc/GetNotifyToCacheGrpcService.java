package com.saga.email.grpc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetNotifyToCacheGrpcService {

    public String getTemplateNotify(String template, String languagesCode) {
        return "Hello world " + template + languagesCode;
    }
}
