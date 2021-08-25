package hexlet.code.Formatters;

import hexlet.code.Utils;

import java.util.Map;
import java.util.List;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.Objects;
import java.util.HashMap;

public class Stylish {
    public static String stylishGenerate(List<Map<String, Object>> diffMap) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> temp = editMapToStylishFormat(diffMap);

        sb.append("{\n")
                .append(Utils.pullStringBuilderWithValues(temp))
                .append("}");
        return sb.toString();
    }

    public static Map<String, Object> editMapToStylishFormat(List<Map<String, Object>> diffList) {
        final int substringForLinter = 4;
        Map<String, Object> temp = new TreeMap<>(Comparator.comparing((String str) ->
                str.substring(substringForLinter))
                .thenComparingInt(str -> " -+".indexOf(str.charAt(2))));

        for (Map<String, Object> map : diffList) {
            for (Map.Entry<String, Object> diff : map.entrySet()) {
                if (Objects.equals(diff.getValue(), "changed")) {
                    temp.putAll(pullMapWithChangedValues(diff.getKey(), map.get("oldValue"), map.get("newValue")));
                }
                if (Objects.equals(diff.getValue(), "added")) {
                    temp.putAll(pullMapWithAddedValues(diff.getKey(), map.get("newValue")));
                }
                if (Objects.equals(diff.getValue(), "unchanged")) {
                    temp.putAll(pullMapWithUnchangedValues(diff.getKey(), map.get("oldValue")));
                }
                if (Objects.equals(diff.getValue(), "deleted")) {
                    temp.putAll(pullMapWithDeletedValues(diff.getKey(), map.get("oldValue")));
                }
            }
        }
        return temp;
    }

    public static Map<String, Object> pullMapWithChangedValues(String key, Object oldValue, Object newValue) {
        Map<String, Object> result = new HashMap<>();
        result.putAll(pullMapWithDeletedValues(key, oldValue));
        result.putAll(pullMapWithAddedValues(key, newValue));
        return result;
    }

    public static Map<String, Object> pullMapWithDeletedValues(String key, Object oldValue) {
        Map<String, Object> result = new HashMap<>();
        result.put("  - " + key + ": ", oldValue + "\n");
        return result;
    }

    public static Map<String, Object> pullMapWithAddedValues(String key, Object newValue) {
        Map<String, Object> result = new HashMap<>();
        result.put("  + " + key + ": ", newValue + "\n");
        return result;
    }

    public static Map<String, Object> pullMapWithUnchangedValues(String key, Object oldValue) {
        Map<String, Object> result = new HashMap<>();
        result.put("    " + key + ": ", oldValue + "\n");
        return result;
    }
}
