package hexlet.code;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.HashSet;


public class TreeAnalyzer {
    public static List<Map<String, Object>> pullMapWithDefaultDiff(Map<String, Object> firstMap,
                                                                   Map<String, Object> secondMap) {
        Set<String> keySet = pullSetWithKeys(firstMap, secondMap);
        List<Map<String, Object>> diffList = new LinkedList<>();

        for (String s : keySet) {
            if (!firstMap.containsKey(s)) {
                Map<String, Object> temp = new LinkedHashMap<>();
                temp.put(s, "added");
                temp.put("newValue", secondMap.get(s));
                diffList.add(temp);
            }
            if (!secondMap.containsKey(s)) {
                Map<String, Object> temp = new LinkedHashMap<>();
                temp.put(s, "deleted");
                temp.put("oldValue", firstMap.get(s));
                diffList.add(temp);
            }
            if (secondMap.containsKey(s)
                    && firstMap.containsKey(s)
                    && !Objects.equals(firstMap.get(s), secondMap.get(s))) {
                Map<String, Object> temp = new LinkedHashMap<>();
                temp.put(s, "changed");
                temp.put("oldValue", firstMap.get(s));
                temp.put("newValue", secondMap.get(s));
                diffList.add(temp);
            }
            if (Objects.equals(firstMap.get(s), secondMap.get(s))) {
                Map<String, Object> temp = new LinkedHashMap<>();
                temp.put(s, "unchanged");
                temp.put("value", firstMap.get(s));
                diffList.add(temp);
            }
        }
        return diffList;
    }

    public static Set<String> pullSetWithKeys(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Set<String> keySet = new HashSet<>(firstMap.keySet());
        keySet.addAll(secondMap.keySet());
        return keySet;
    }
}
