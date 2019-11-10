package com.example.oauth.client.utils;

import lombok.experimental.UtilityClass;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

@UtilityClass
public class JsonUtils {

    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> List<T> jsonStrToList(String content, Class clazz) {
        try {
            return OBJECT_MAPPER.readValue(content, OBJECT_MAPPER.getTypeFactory().constructType(List.class, clazz));
        } catch (IOException e) {
            throw new JsonConvertExeception(e.getMessage(), e);
        }
    }
}
