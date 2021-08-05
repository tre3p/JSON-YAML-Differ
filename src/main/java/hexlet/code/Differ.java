package hexlet.code;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;


public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
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
        return Formatter.formatter(format, temp, firstMap, secondMap);
    }
}
