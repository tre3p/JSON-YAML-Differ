package hexlet.code;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.HashSet;


public class TreeAnalyzer {
    public static List<Map<String, Object>> analyzeDiff(Map<String, Object> firstMap,
                                                                   Map<String, Object> secondMap) {
        Set<String> keySet = pullSetWithKeys(firstMap, secondMap);
        List<Map<String, Object>> analyzedDiffs = new LinkedList<>();

        for (String s : keySet) {
            Map<String, Object> temp = new LinkedHashMap<>();
            if (!firstMap.containsKey(s)) {
                temp.put(s, "added");
                temp.put("newValue", secondMap.get(s));
            }
            if (!secondMap.containsKey(s)) {
                temp.put(s, "deleted");
                temp.put("oldValue", firstMap.get(s));
            }
            if (secondMap.containsKey(s)
                    && firstMap.containsKey(s)
                    && !Objects.equals(firstMap.get(s), secondMap.get(s))) {
                temp.put(s, "changed");
                temp.put("oldValue", firstMap.get(s));
                temp.put("newValue", secondMap.get(s));
            }
            if (Objects.equals(firstMap.get(s), secondMap.get(s))) {
                temp.put(s, "unchanged");
                temp.put("oldValue", firstMap.get(s));
            }
            analyzedDiffs.add(temp);
        }
        return analyzedDiffs;
    }

    public static Set<String> pullSetWithKeys(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Set<String> keySet = new HashSet<>(firstMap.keySet());
        keySet.addAll(secondMap.keySet());
        return keySet;
    }
}
