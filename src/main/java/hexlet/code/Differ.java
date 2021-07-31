package hexlet.code;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


public class Differ {
    public static String generate(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> temp = new TreeMap<>(Comparator.comparing((String str) -> str.startsWith("  + ") ? 1 : -1)
                .thenComparing(str -> str.substring("  + ".length())));
        sb.append("{\n");

        for (String firstKey : firstMap.keySet()) {
            for (String secondKey : secondMap.keySet()) {
                if (secondMap.containsKey(secondKey) && !firstMap.containsKey(secondKey)) {
                    temp.put("  + " + secondKey + ": ", secondMap.get(secondKey) + "\n");
                }
                if (firstMap.containsKey(firstKey) && !secondMap.containsKey(firstKey)) {
                    temp.put("  - " + firstKey + ": ", firstMap.get(firstKey) + "\n");
                }
                if (firstMap.containsKey(firstKey)
                        && secondMap.containsKey(firstKey)
                        && !firstMap.get(firstKey).equals(secondMap.get(firstKey))) {
                    temp.put("  + " + firstKey + ": ", secondMap.get(firstKey) + "\n");
                    temp.put("  - " + firstKey + ": ", firstMap.get(firstKey) + "\n");
                }
                if (firstMap.containsKey(firstKey)
                        && secondMap.containsKey(secondKey)
                        && firstMap.get(firstKey).equals(secondMap.get(firstKey))) {
                    temp.put("    " + firstKey + ": ", firstMap.get(firstKey) + "\n");
                }

            }
        }
        for (Map.Entry<String, Object> test : temp.entrySet()) {
            sb.append(test.getKey()).append(test.getValue());
        }
        sb.append("}");
        System.out.println(sb);
        return String.valueOf(sb);
    }
}
