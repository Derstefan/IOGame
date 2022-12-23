package org.iogame;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

public class JsonHelper {

    public static <T> String serializeObject(T object) {
        StringWriter stringWriter = new StringWriter();
        try {
            new ObjectMapper().writeValue(stringWriter, object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringWriter.toString();
    }
}
