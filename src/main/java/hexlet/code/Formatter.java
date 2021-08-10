package hexlet.code;

import hexlet.code.Formatters.Json;
import hexlet.code.Formatters.Plain;
import hexlet.code.Formatters.Stylish;
import java.io.IOException;
import java.util.Map;

public class Formatter {
    public static String formatter(String format,
                                   Map<String, String> template,
                                   Map<String, Object> firstMap,
                                   Map<String, Object> secondMap) throws IOException {
        String result = null;

        if (format.equals("stylish")) {
            result = Stylish.stylishGenerate(template, firstMap, secondMap);
        }
        if (format.equals("plain")) {
            result = Plain.plainGenerate(template, firstMap, secondMap);
        }
        if (format.equals("json")) {
            result = Json.jsonGenerate(template, firstMap, secondMap);
        }
        return result;
    }
}
