package hexlet.code.Formatters;

import hexlet.code.Utils;

import java.util.Map;
import java.util.List;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Objects;

public class Stylish {
    public static String stylishGenerator(List<Map<String, Object>> diffMap) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> temp = editMapToStylishFormat(diffMap);

        sb.append("{\n")
                .append(Utils.pullStringBuilderWithValues(temp))
                .append("}");
        return sb.toString();
    }

    private static Map<String, Object> editMapToStylishFormat(List<Map<String, Object>> diffList) {
        final int substringForLinter = 4;
        Map<String, Object> temp = new TreeMap<>(Comparator.comparing((String str) ->
                str.substring(substringForLinter))
                .thenComparingInt(str -> " -+".indexOf(str.charAt(2))));

        for (Map<String, Object> map : diffList) {
            temp.putAll(mapAnalyzer(map));
        }
        return temp;
    }

    private static Map<String, Object> mapAnalyzer(Map<String, Object> map) {
        Map<String, Object> result = new LinkedHashMap<>();

        if (Objects.equals(map.get("status"), "changed")) {
            result.put("  - " + map.get("field") + ": ", map.get("oldValue") + "\n");
            result.put("  + " + map.get("field") + ": ", map.get("newValue") + "\n");
        }

        if (Objects.equals(map.get("status"), "deleted")) {
            result.put("  - " + map.get("field") + ": ", map.get("oldValue") + "\n");
        }

        if (Objects.equals(map.get("status"), "added")) {
            result.put("  + " + map.get("field") + ": ", map.get("newValue") + "\n");
        }

        if (Objects.equals(map.get("status"), "unchanged")) {
            result.put("    " + map.get("field") + ": ", map.get("oldValue") + "\n");
        }
        return result;
    }
}
