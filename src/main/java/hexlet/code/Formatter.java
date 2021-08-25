package hexlet.code;

import hexlet.code.Formatters.Json;
import hexlet.code.Formatters.Plain;
import hexlet.code.Formatters.Stylish;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatDiff(String format, List<Map<String, Object>> diff) throws IOException {
        if (format.equals("stylish")) {
            return Stylish.stylishGenerate(diff);
        }
        if (format.equals("plain")) {
            return Plain.plainGenerate(diff);
        }
        if (format.equals("json")) {
            return Json.jsonGenerate(diff);
        }
        return null;
    }
}
