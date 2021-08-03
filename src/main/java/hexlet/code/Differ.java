package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Formatters.Plain;
import hexlet.code.Formatters.Stylish;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;


public class Differ {
    public static String generate(String format,
                               String filepath1,
                               String filepath2) throws Exception {
        String result;
        Map<String, Object> firstMap = Parser.parsFile(filepath1);
        Map<String, Object> secondMap = Parser.parsFile(filepath2);
        Map<String, String> temp = new HashMap<>();

        for (String firstKey : firstMap.keySet()) {
            for (String secondKey : secondMap.keySet()) {
                if (secondMap.containsKey(secondKey) && !firstMap.containsKey(secondKey)) {
                    temp.put(secondKey, "added");
                }
                if (firstMap.containsKey(firstKey) && !secondMap.containsKey(firstKey)) {
                    temp.put(firstKey, "deleted");
                }
                if (firstMap.containsKey(firstKey)
                        && secondMap.containsKey(firstKey)
                        && !Objects.equals(firstMap.get(firstKey), secondMap.get(firstKey))) {
                    temp.put(firstKey, "changed");
                }
                if (firstMap.containsKey(firstKey)
                        && secondMap.containsKey(secondKey)
                        && Objects.equals(firstMap.get(firstKey), secondMap.get(firstKey))) {
                    temp.put(firstKey, "unchanged");
                }
            }
        }

        if (format.equals("stylish")) {
            return Stylish.stylishGenerate(temp, firstMap, secondMap);
        } else if (format.equals("plain")) {
            return Plain.plainGenerate(temp, firstMap, secondMap);
        } else if (format.equals("json")) {
            ObjectMapper jsonMapper = new ObjectMapper();
            result = Stylish.stylishGenerate(temp, firstMap, secondMap);
            jsonMapper.writeValue(new File("src/output/result.json"),
                    result.replaceAll("\\n", "").replaceAll("    ", ""));
            return result;
        }
        return null;
    }
}
