package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parseToMap(String fileToString, String extension) throws JsonProcessingException {
        if (extension.endsWith(".json")) {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            return jsonObjectMapper.readValue(fileToString, Map.class);
        }
        if (extension.endsWith(".yaml") || extension.endsWith(".yml")) {
            ObjectMapper yamlObjectMapper = new ObjectMapper(new YAMLFactory());
            return yamlObjectMapper.readValue(fileToString, Map.class);
        }
        return new HashMap<>();
    }
}
