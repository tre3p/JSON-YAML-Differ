package hexlet.code;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parsFile(String path) throws IOException {
        Map<String, Object> result = new HashMap<>();
        if (path.endsWith(".json")) {
            result = Differ.jsonParse(path);
        } else if (path.endsWith(".yaml") || path.endsWith(".yml")) {
            result = Differ.yamlParse(path);
        }
        return result;
    }
}
