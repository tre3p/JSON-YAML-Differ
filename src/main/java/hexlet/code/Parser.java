package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parsFile(String path) throws Exception {
        if (path.endsWith(".json")) {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            Map<String, Object> jsonResultMap = jsonObjectMapper.readValue(new File(path), new TypeReference<>() { });
            return jsonResultMap;
        } else if (path.endsWith(".yaml") || path.endsWith(".yml")) {
            ObjectMapper yamlObjectMapper = new ObjectMapper(new YAMLFactory());
            Map<String, Object> resultYamlMap = yamlObjectMapper.readValue(new File(path),
                    new TypeReference<>() { });
            return resultYamlMap;
        }
        return null;
    }
}
