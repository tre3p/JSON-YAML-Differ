package hexlet.code.Formatters;

import hexlet.code.Utils;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.Objects;


public class Plain {
    public static String plainGenerator(List<Map<String, Object>> diffList) {
        List<Map<String, Object>> editedDiffList = complexValueMapFormatter(diffList);
        Map<String, Object> plainResult = editMapToPlainFormat(editedDiffList);
        StringBuilder sb = new StringBuilder();
        sb.append(Utils.pullStringBuilderWithValues(plainResult));
        sb.deleteCharAt(sb.lastIndexOf("\n"));
        return sb.toString();
    }

    private static List<Map<String, Object>> complexValueMapFormatter(List<Map<String, Object>> diffList) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        for (Map<String, Object> map : diffList) {
            for (Map.Entry<String, Object> entrySet : map.entrySet()) {
                if (entrySet.getValue() instanceof Map
                        || entrySet.getValue() instanceof List
                        || entrySet.getValue() instanceof Arrays) {
                    map.put(entrySet.getKey(), "[complex value]");

                }

                if (entrySet.getValue() instanceof String && !entrySet.getValue().equals("[complex value]")) {
                    map.put(entrySet.getKey(), "'" + map.get(entrySet.getKey()) + "'");
                }
                resultList.add(map);
            }
        }
        return resultList;
    }

    private static Map<String, Object> editMapToPlainFormat(List<Map<String, Object>> diffList) {
        final int substringForLinter = 10;
        Map<String, Object> result = new TreeMap<>(Comparator.comparing((String str) ->
                str.substring(substringForLinter))
                .thenComparingInt(str -> "Property ".indexOf(str.charAt(2))));

        for (Map<String, Object> map : diffList) {
            result.putAll(diffAnalyzer(map));
        }
        return result;
    }

    private static Map<String, Object> diffAnalyzer(Map<String, Object> map) {
        Map<String, Object> result = new LinkedHashMap<>();

        if (Objects.equals(map.get("status"), "'changed'")) {
            result.put("Property " + map.get("field"), " was updated. From "
                            + map.get("oldValue")
                            + " to "
                            + map.get("newValue") + "\n");
        }

        if (Objects.equals(map.get("status"), "'deleted'")) {
            result.put("Property " + map.get("field"), " was removed\n");
        }

        if (Objects.equals(map.get("status"), "'added'")) {
            result.put("Property " + map.get("field"), " was added with value: " + map.get("newValue") + "\n");
        }
        return result;
    }
}
