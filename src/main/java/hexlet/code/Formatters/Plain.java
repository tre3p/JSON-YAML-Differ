package hexlet.code.Formatters;

import hexlet.code.Utils;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.util.TreeMap;
import java.util.Arrays;


public class Plain {
    public static String plainGenerate(Map<String, String> keys,
                                       Map<String, Object> firstMap,
                                       Map<String, Object> secondMap) {
        final int substringForLinter = 10;
        StringBuilder sb = new StringBuilder();
        firstMap = mapFormatter(firstMap);
        secondMap = mapFormatter(secondMap);
        Map<String, Object> temp = editMapToPlainFormat(keys, firstMap, secondMap);
        return String.valueOf(Utils.pullStringBuilderWithValues(temp, sb)).trim();
    }

    public static Map<String, Object> mapFormatter(Map<String, Object> map) {
        for (Map.Entry<String, Object> firstEntrySet : map.entrySet()) {
            if (firstEntrySet.getValue() instanceof Map
                    || firstEntrySet.getValue() instanceof List
                    || firstEntrySet.getValue() instanceof Arrays) {
                map.put(firstEntrySet.getKey(), "[complex value]");
            }

            if (firstEntrySet.getValue() instanceof String && !firstEntrySet.getValue().equals("[complex value]")) {
                map.put(firstEntrySet.getKey(), "'" + map.get(firstEntrySet.getKey()) + "'");
            }
        }
        return map;
    }

    public static Map<String, Object> editMapToPlainFormat(Map<String, String> keys,
                                                      Map<String, Object> firstMap,
                                                      Map<String, Object> secondMap) {
        final int substringForLinter = 10;
        Map<String, Object> temp = new TreeMap<>(Comparator.comparing((String str) ->
                str.substring(substringForLinter))
                .thenComparingInt(str -> "Property ".indexOf(str.charAt(2))));

        for (Map.Entry<String, String> map : keys.entrySet()) {
            switch (map.getValue()) {
                case "added":
                    temp.put("Property '"
                            + map.getKey(), "' was added with value: "
                            + secondMap.get(map.getKey()) + "\n");
                    break;
                case "changed":
                    temp.put("Property '"
                            + map.getKey(), "' was updated. From "
                            + firstMap.get(map.getKey()) + " to "
                            + secondMap.get(map.getKey()) + "\n");
                    break;
                case "deleted":
                    temp.put("Property '" + map.getKey(), "' was removed\n");
                    break;
                default:
                    break;
            }
        }
        return temp;
    }
}
