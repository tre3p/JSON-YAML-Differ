package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Formation {
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

    public static StringBuilder pullStringBuilderWithValues(Map<String, Object> map, StringBuilder sb) {
        for (Map.Entry<String, Object> test : map.entrySet()) {
            sb.append(test.getKey()).append(test.getValue());
        }
        return sb;
    }
}
