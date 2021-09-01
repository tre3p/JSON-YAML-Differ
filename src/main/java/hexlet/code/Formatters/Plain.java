package hexlet.code.Formatters;

import hexlet.code.Utils;

import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Objects;

public class Plain {
    public static String plainGenerator(List<Map<String, Object>> diffList) {
        Map<String, Object> plainResult = editMapToPlainFormat(diffList);
        StringBuilder sb = new StringBuilder();
        sb.append(Utils.pullStringBuilderWithValues(plainResult));
        sb.deleteCharAt(sb.lastIndexOf("\n"));
        return sb.toString();
    }

    private static Object complexValueMapFormatter(Object object) {
        if (object instanceof Arrays || object instanceof List || object instanceof Map) {
            return "[complex value]";
        }
        if (object instanceof String) {
            return "'" + object + "'";
        }
        return object;
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

        if (Objects.equals(map.get("status"), "changed")) {
            result.put("Property '" + map.get("field"), "' was updated. From "
                            + complexValueMapFormatter(map.get("oldValue"))
                            + " to "
                            + complexValueMapFormatter(map.get("newValue")) + "\n");
        }

        if (Objects.equals(map.get("status"), "deleted")) {
            result.put("Property '" + map.get("field"), "' was removed\n");
        }

        if (Objects.equals(map.get("status"), "added")) {
            result.put("Property '" + map.get("field"), "' was added with value: "
                    + complexValueMapFormatter(map.get("newValue")) + "\n");
        }
        return result;
    }
}
