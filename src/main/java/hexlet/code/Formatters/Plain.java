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
    public static String plainGenerate(List<Map<String, Object>> defaultDiffList) {
        StringBuilder sb = new StringBuilder();
        List<Map<String, Object>> editedDefList = mapFormatter(defaultDiffList);
        Map<String, Object> plainResult = editMapToPlainFormat(editedDefList);
        sb.append(Utils.pullStringBuilderWithValues(plainResult));
        sb.deleteCharAt(sb.lastIndexOf("\n"));
        return sb.toString();
    }

    public static List<Map<String, Object>> mapFormatter(List<Map<String, Object>> defaultDiffList) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        for (Map<String, Object> map : defaultDiffList) {
            for (Map.Entry<String, Object> firstEntrySet : map.entrySet()) {
                if (firstEntrySet.getValue() instanceof Map
                        || firstEntrySet.getValue() instanceof List
                        || firstEntrySet.getValue() instanceof Arrays) {
                    map.put(firstEntrySet.getKey(), "[complex value]");
                }

                if (firstEntrySet.getValue() instanceof String && !firstEntrySet.getValue().equals("[complex value]")) {
                    map.put(firstEntrySet.getKey(), "'" + map.get(firstEntrySet.getKey()) + "'");
                }
                resultList.add(map);
            }
        }
        return resultList;
    }

    public static Map<String, Object> editMapToPlainFormat(List<Map<String, Object>> defaultDiffList) {
        final int substringForLinter = 10;
        Map<String, Object> result = new TreeMap<>(Comparator.comparing((String str) ->
                str.substring(substringForLinter))
                .thenComparingInt(str -> "Property ".indexOf(str.charAt(2))));

        for (Map<String, Object> map : defaultDiffList) {
            result.putAll(diffAnalyzer(map));
        }
        return result;
    }

    public static Map<String, Object> diffAnalyzer(Map<String, Object> map) {
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
