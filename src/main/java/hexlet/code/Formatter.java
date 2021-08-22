package hexlet.code;

import hexlet.code.Formatters.Json;
import hexlet.code.Formatters.Plain;
import hexlet.code.Formatters.Stylish;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatter(String format,
                                   List<Map<String, Object>> defaultDiffList) throws IOException {
        String result = null;

        if (format.equals("stylish")) {
            result = Stylish.stylishGenerate(defaultDiffList);
        }
        if (format.equals("plain")) {
            result = Plain.plainGenerate(defaultDiffList);
        }
        if (format.equals("json")) {
            //result = Json.jsonGenerate(defaultDiffList);
        }
        return result;
    }
}
