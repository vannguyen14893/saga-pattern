package com.example.utils.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Mapper {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new JavaTimeModule());

    public static <T> T parseJsonToObject(String jSonObject, Class<T> serializableClass) {
        T objects = null;
        try {
            objects = JSON_MAPPER.readValue(jSonObject, serializableClass);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return objects;
    }

    public static String toObjectString(Object serializableObject) {
        String jsonResponseString = null;
        try {
            jsonResponseString = JSON_MAPPER.writeValueAsString(serializableObject);
        } catch (JsonProcessingException ex) {
        }
        return jsonResponseString;
    }

}
