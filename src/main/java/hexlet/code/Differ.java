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
        Map<String, String> defaultDiffMap = pullMapWithDefaultDiff(firstMap, secondMap);
        return Formatter.formatter(format, defaultDiffMap, firstMap, secondMap);
    }

    public static Map<String, String> pullMapWithDefaultDiff(Map<String, Object> firstMap,
                                                             Map<String, Object> secondMap) {
        Map<String, String> defaultDiffMap = new HashMap<>();

        for (String firstKey : firstMap.keySet()) {
            for (String secondKey : secondMap.keySet()) {
                if (secondMap.containsKey(secondKey) && !firstMap.containsKey(secondKey)) {
                    defaultDiffMap.put(secondKey, "added");
                }
                if (firstMap.containsKey(firstKey) && !secondMap.containsKey(firstKey)) {
                    defaultDiffMap.put(firstKey, "deleted");
                }
                if (firstMap.containsKey(firstKey)
                    && secondMap.containsKey(firstKey)
                    && !Objects.equals(firstMap.get(firstKey), secondMap.get(firstKey))) {
                    defaultDiffMap.put(firstKey, "changed");
                }
                if (firstMap.containsKey(firstKey)
                    && secondMap.containsKey(secondKey)
                    && Objects.equals(firstMap.get(firstKey), secondMap.get(firstKey))) {
                    defaultDiffMap.put(firstKey, "unchanged");
                }
            }
        }
        return defaultDiffMap;
    }
}
