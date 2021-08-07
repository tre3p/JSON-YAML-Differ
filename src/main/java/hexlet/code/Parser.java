package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.util.Map;

public class Parser {
    private Map<String, Object> resultMap;
    public static Map<String, Object> parsFile(String path) throws Exception {
        Map<String, Object> resultMap = null;
        if (path.endsWith(".json")) {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            resultMap = jsonObjectMapper.readValue(new File(path), new TypeReference<>() { });
        } else if (path.endsWith(".yaml") || path.endsWith(".yml")) {
            ObjectMapper yamlObjectMapper = new ObjectMapper(new YAMLFactory());
            resultMap = yamlObjectMapper.readValue(new File(path), new TypeReference<>() { });
        }
        return resultMap;
    }
}
