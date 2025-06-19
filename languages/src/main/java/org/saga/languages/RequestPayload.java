package org.saga.languages;

import lombok.Data;

import java.util.Map;

@Data
public class RequestPayload {

    private ConvertCommon convert_common;
    @Data
    public static class ConvertCommon {
        private String welcome;
        private Map<String, String> buttons;
    }
}
