package hexlet.code.Formatters;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Plain {
    public static String plainGenerate(Map<String, String> keys,
                                       Map<String, Object> firstMap,
                                       Map<String, Object> secondMap) {
        final int substringForLinter = 10;
        StringBuilder sb = new StringBuilder();
        Map<String, Object> temp = new TreeMap<>(Comparator.comparing((String str) ->
                str.substring(substringForLinter))
                .thenComparingInt(str -> "Property ".indexOf(str.charAt(2)))
        );

        for (Map.Entry<String, String> map : keys.entrySet()) {
            switch (map.getValue()) {
                case "added":
                    temp.put("Property '"
                            + map.getKey(), "' was added with value '"
                            + secondMap.get(map.getKey()) + "'\n");
                    break;
                case "changed":
                    temp.put("Property '"
                            + map.getKey(), "' was updated. From '"
                            + firstMap.get(map.getKey()) + "' to '"
                            + secondMap.get(map.getKey()) + "'\n");
                    break;
                case "deleted":
                    temp.put("Property '" + map.getKey(), "' was removed\n");
                    break;
                default:
            }
        }

        for (Map.Entry<String, Object> test : temp.entrySet()) {
            sb.append(test.getKey()).append(test.getValue());
        }
        System.out.println(sb);
        return String.valueOf(sb);
    }
}
