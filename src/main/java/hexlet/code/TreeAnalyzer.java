package hexlet.code;


import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.HashSet;


public class TreeAnalyzer {
    public static List<Map<String, Object>> analyzeDiff(Map<String, Object> firstMap,
                                                                   Map<String, Object> secondMap) {
        Set<String> keySet = pullSetWithKeys(firstMap, secondMap);
        List<Map<String, Object>> analyzedDiffs = new ArrayList<>();

        for (String s : keySet) {
            Map<String, Object> temp = new LinkedHashMap<>();
            if (!firstMap.containsKey(s)) {
                temp.putAll(pullMapWithValues(s, "added", secondMap.get(s), secondMap.get(s)));
            }
            if (!secondMap.containsKey(s)) {
                temp.putAll(pullMapWithValues(s, "deleted", firstMap.get(s), firstMap.get(s)));
            }
            if (secondMap.containsKey(s)
                    && firstMap.containsKey(s)
                    && !Objects.equals(firstMap.get(s), secondMap.get(s))) {
                temp.putAll(pullMapWithValues(s, "changed", firstMap.get(s), secondMap.get(s)));
            }
            if (Objects.equals(firstMap.get(s), secondMap.get(s))) {
                temp.putAll(pullMapWithValues(s, "unchanged", firstMap.get(s), firstMap.get(s)));
            }
            analyzedDiffs.add(temp);
        }
        return analyzedDiffs;
    }

    private static Map<String, Object> pullMapWithValues(String key, String status, Object oldValue, Object newValue) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("field", key);
        result.put("status", status);
        result.put("oldValue", oldValue);
        result.put("newValue", newValue);
        return result;
    }

    private static Set<String> pullSetWithKeys(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Set<String> keySet = new HashSet<>(firstMap.keySet());
        keySet.addAll(secondMap.keySet());
        return keySet;
    }
}
