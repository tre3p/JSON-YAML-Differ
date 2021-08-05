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
            String result = Stylish.stylishGenerate(template, firstMap, secondMap);
            System.out.println(result);
            return result;
        }
        if (format.equals("plain")) {
            String result = Plain.plainGenerate(template, firstMap, secondMap);
            System.out.println(result);
            return result;
        }
        if (format.equals("json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            String result = objectMapper.writeValueAsString(Stylish.stylishGenerate(template, firstMap, secondMap));
            result = result.replaceAll("\\n", "");
            System.out.println(result);
            return result;
        }
        return null;
    }
}
