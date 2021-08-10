package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> firstMap = Parser.parsFile(filepath1);
        Map<String, Object> secondMap = Parser.parsFile(filepath2);
        Map<String, String> defaultDiffMap = Formation.pullMapWithDefaultDiff(firstMap, secondMap);
        return Formatter.formatter(format, defaultDiffMap, firstMap, secondMap);
    }

    public static Map<String, Object> jsonParse(String path) throws IOException {
        ObjectMapper jsonObjectMapper = new ObjectMapper();
        return jsonObjectMapper.readValue(new File(path), new TypeReference<>() { });
    }

    public static Map<String, Object> yamlParse(String path) throws IOException {
        ObjectMapper yamlObjectMapper = new ObjectMapper(new YAMLFactory());
        return yamlObjectMapper.readValue(new File(path), new TypeReference<>() { });
    }
}
