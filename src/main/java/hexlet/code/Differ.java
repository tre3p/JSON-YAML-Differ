package hexlet.code;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;


public class Differ {
    public static Map generate(Map<String, Object> firstMap, Map<String, Object> secondMap) {
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
        return temp;
    }
}
