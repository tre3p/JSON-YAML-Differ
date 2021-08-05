package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Formatters.Plain;
import hexlet.code.Formatters.Stylish;
import java.io.IOException;
import java.util.Map;

public class Formatter {
    public static String formatter(String format,
                                   Map<String, String> template,
                                   Map<String, Object> firstMap,
                                   Map<String, Object> secondMap) throws IOException {

        if (format.equals("stylish")) {
            return Stylish.stylishGenerate(template, firstMap, secondMap);
        }
        if (format.equals("plain")) {
            return Plain.plainGenerate(template, firstMap, secondMap);
        }
        if (format.equals("json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            String result = Stylish.stylishGenerate(template, firstMap, secondMap);
            return objectMapper.writeValueAsString(result);
        }
        return null;
    }
}
