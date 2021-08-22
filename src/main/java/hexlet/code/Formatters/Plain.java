package hexlet.code.Formatters;

import hexlet.code.TreeAnalyzer;
import hexlet.code.Utils;

import java.util.*;


public class Plain {
    public static String plainGenerate(List<Map<String, Object>> defaultDiffList) {
        StringBuilder sb = new StringBuilder();
        List<Map<String, Object>> editedDefList = mapFormatter(defaultDiffList);
        Map<String, Object> plainResult = editMapToPlainFormat(editedDefList);
        return String.valueOf(Utils.pullStringBuilderWithValues(plainResult, sb)).trim();
    }

    public static List<Map<String, Object>> mapFormatter(List<Map<String, Object>> defaultDiffList) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        for (Map map : defaultDiffList) {
            Map<String, Object> temp = new HashMap<>(map);
            for (Map.Entry<String, Object> firstEntrySet : temp.entrySet()) {
                if (firstEntrySet.getValue() instanceof Map
                        || firstEntrySet.getValue() instanceof List
                        || firstEntrySet.getValue() instanceof Arrays) {
                    temp.put(firstEntrySet.getKey(), "[complex value]");
                }

                if (firstEntrySet.getValue() instanceof String && !firstEntrySet.getValue().equals("[complex value]")) {
                    temp.put(firstEntrySet.getKey(), "'" + temp.get(firstEntrySet.getKey()) + "'");
                }
                resultList.add(temp);
            }
        }
        return resultList;
    }

    public static Map<String, Object> editMapToPlainFormat(List<Map<String, Object>> defaultDiffList) {
        final int substringForLinter = 10;
        Map<String, Object> result = new TreeMap<>(Comparator.comparing((String str) ->
                str.substring(substringForLinter))
                .thenComparingInt(str -> "Property ".indexOf(str.charAt(2))));

        for (Map map : defaultDiffList) {
            Map<String, Object> temp = new HashMap<>(map);
            for (Map.Entry<String, Object> diff : temp.entrySet()) {
                if (Objects.equals(diff.getValue(), "'added'")) {
                    result.put("Property '"
                            + diff.getKey(), "' was added with value: "
                            + map.get("newValue") + "\n");
                }
                if (Objects.equals(diff.getValue(), "'changed'")) {
                    result.put("Property '"
                            + diff.getKey(), "' was updated. From "
                            + map.get("oldValue") + " to "
                            + map.get("newValue") + "\n");

                }
                if (Objects.equals(diff.getValue(), "'deleted'")) {
                    result.put("Property '" + diff.getKey(), "' was removed\n");
                }
            }
        }
        return result;
    }
}
