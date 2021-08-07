package hexlet.code.Formatters;

import hexlet.code.Utils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Stylish {
    public static String stylishGenerate(Map<String, String> keys,
                                         Map<String, Object> firstMap,
                                         Map<String, Object> secondMap) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> temp = editMapToStylishFormat(keys, firstMap, secondMap);

        sb.append("{\n");
        Utils.pullStringBuilderWithValues(temp, sb);
        sb.append("}");
        return String.valueOf(sb).trim();
    }

    public static Map<String, Object> editMapToStylishFormat(Map<String, String> keys,
                                                      Map<String, Object> firstMap,
                                                      Map<String, Object> secondMap) {
        final int substringForLinter = 4;
        Map<String, Object> temp = new TreeMap<>(Comparator.comparing((String str) ->
                str.substring(substringForLinter))
                .thenComparingInt(str -> " -+".indexOf(str.charAt(2))));

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
                    break;
            }
        }
        return temp;
    }
}
