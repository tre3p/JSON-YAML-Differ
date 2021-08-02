package hexlet.code;


import hexlet.code.Formatters.Plain;
import hexlet.code.Formatters.Stylish;
import java.util.Map;

public class Formatter {
    public static void chooseFormat(String format,
                                    Map<String, String> temp,
                                    Map<String, Object> firstMap,
                                    Map<String, Object> secondMap) {
        if (format.equals("stylish")) {
            Stylish.stylishGenerate(temp, firstMap, secondMap);
        } else if (format.equals("plain")) {
            Plain.plainGenerate(temp, firstMap, secondMap);
        }
    }
}
