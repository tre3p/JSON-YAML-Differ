package hexlet.code.Formatters;

import hexlet.code.Utils;

import java.util.*;

public class Stylish {
    public static String stylishGenerate(List<Map<String, Object>> diffMap) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> temp = editMapToStylishFormat(diffMap);

        sb.append("{\n");
        Utils.pullStringBuilderWithValues(temp, sb);
        sb.append("}");
        return sb.toString();
    }

    public static Map<String, Object> editMapToStylishFormat(List<Map<String, Object>> diffList) {
        final int substringForLinter = 4;
        Map<String, Object> temp = new TreeMap<>(Comparator.comparing((String str) ->
                str.substring(substringForLinter))
                .thenComparingInt(str -> " -+".indexOf(str.charAt(2))));

        for (Map map : diffList) {
            Map<String, Object> diffMap = new LinkedHashMap<>(map);
            for (Map.Entry<String, Object> diff : diffMap.entrySet()) {
                if (Objects.equals(diff.getValue(), "changed")) {
                    temp.put("  - " + diff.getKey() + ": ", map.get("oldValue") + "\n");
                    temp.put("  + " + diff.getKey() + ": ", map.get("newValue") + "\n");
                }
                if (Objects.equals(diff.getValue(), "added")) {
                    temp.put("  + " + diff.getKey() + ": ", map.get("newValue") + "\n");
                }
                if (Objects.equals(diff.getValue(), "unchanged")) {
                    temp.put("    " + diff.getKey() + ": ", map.get("value") + "\n");
                }
                if (Objects.equals(diff.getValue(), "deleted")) {
                    temp.put("  - " + diff.getKey() + ": ", map.get("oldValue") + "\n");
                }
            }
        }
        return temp;
    }
}
