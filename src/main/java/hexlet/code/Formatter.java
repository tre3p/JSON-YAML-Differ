package hexlet.code;


import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Formatters.Plain;
import hexlet.code.Formatters.Stylish;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Formatter {
    public static void chooseFormat(String format,
                                    Map<String, String> temp,
                                    Map<String, Object> firstMap,
                                    Map<String, Object> secondMap) throws IOException {
        if (format.equals("stylish")) {
            Stylish.stylishGenerate(temp, firstMap, secondMap);
        } else if (format.equals("plain")) {
            Plain.plainGenerate(temp, firstMap, secondMap);
        } else if (format.equals("json")) {
            ObjectMapper jsonMapper = new ObjectMapper();
            String json = Stylish.stylishGenerate(temp, firstMap, secondMap);
            jsonMapper.writeValue(new File("src/output/result.json"),
                    json.replaceAll("\\n", "").replaceAll("    ", ""));
        }
    }
}
