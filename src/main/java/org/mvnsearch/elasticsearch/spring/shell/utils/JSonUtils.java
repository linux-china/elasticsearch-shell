package org.mvnsearch.elasticsearch.spring.shell.utils;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * json utils
 *
 * @author linux_china
 */
public class JSonUtils {
    /**
     * jackson object mapper, thread safe
     */
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * get jackson object mapper
     *
     * @return object mapper
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }


    /**
     * parse json text and converted to Map
     *
     * @param jsonText json text
     * @return map object
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> parseJson(String jsonText) {
        try {
            ObjectMapper mapper = getObjectMapper();
            return mapper.readValue(jsonText, Map.class);
        } catch (Exception ignore) {
            return new HashMap<String, Object>();
        }
    }


    /**
     * convert data to json text
     *
     * @param data data
     * @return json text
     */
    public static String toJson(Object data, boolean pretty) {
        ObjectMapper objectMapper = getObjectMapper();
        try {
            if (pretty) {
                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            } else {
                return objectMapper.writeValueAsString(data);
            }
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
        return null;
    }
}
