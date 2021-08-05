package hexlet.code.Formatters;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Stylish {
    public static String stylishGenerate(Map<String, String> keys,
                                         Map<String, Object> firstMap,
                                         Map<String, Object> secondMap) {
        final int substringForLinter = 4;
        StringBuilder sb = new StringBuilder();
        Map<String, Object> temp = new TreeMap<>(Comparator.comparing((String str) ->
                str.substring(substringForLinter))
                .thenComparingInt(str -> " -+".indexOf(str.charAt(2)))
        );
        sb.append("{\n");
        for (Map.Entry<String, String> map : keys.entrySet()) {
            switch (map.getValue()) {
                case "added":
                    temp.put("  + " + map.getKey() + ": ", secondMap.get(map.getKey()) + "\n");
                    break;
                case "changed":
                    temp.put("  - " + map.getKey() + ": ", firstMap.get(map.getKey()) + "\n");
                    temp.put("  + " + map.getKey() + ": ", secondMap.get(map.getKey()) + "\n");
                    break;
                case "unchanged":
                    temp.put("    " + map.getKey() + ": ", secondMap.get(map.getKey()) + "\n");
                    break;
                case "deleted":
                    temp.put("  - " + map.getKey() + ": ", firstMap.get(map.getKey()) + "\n");
                    break;
                default:
            }
        }
        for (Map.Entry<String, Object> test : temp.entrySet()) {
            sb.append(test.getKey()).append(test.getValue());
        }
        sb.append("}");
        return String.valueOf(sb);
    }
}
