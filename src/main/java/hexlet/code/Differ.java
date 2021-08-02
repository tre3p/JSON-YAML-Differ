package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.Comparator;


public class Differ {
    public static String stylishGenerate(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        final int substringForLinter = 4;
        StringBuilder sb = new StringBuilder();
        Map<String, Object> temp = new TreeMap<>(Comparator.comparing((String str) ->
                str.substring(substringForLinter))
                .thenComparingInt(str -> " -+".indexOf(str.charAt(2)))
        );
        sb.append("{\n");
        for (String firstKey : firstMap.keySet()) {
            for (String secondKey : secondMap.keySet()) {
                if (secondMap.containsKey(secondKey) && !firstMap.containsKey(secondKey)) {
                    temp.put("  + " + secondKey + ": ", secondMap.get(secondKey) + "\n");
                }
                if (firstMap.containsKey(firstKey) && !secondMap.containsKey(firstKey)) {
                    temp.put("  - " + firstKey + ": ", firstMap.get(firstKey) + "\n");
                }
                if (firstMap.containsKey(firstKey)
                        && secondMap.containsKey(firstKey)
                        && !Objects.equals(firstMap.get(firstKey), secondMap.get(firstKey))) {
                    temp.put("  + " + firstKey + ": ", secondMap.get(firstKey) + "\n");
                    temp.put("  - " + firstKey + ": ", firstMap.get(firstKey) + "\n");
                }
                if (firstMap.containsKey(firstKey)
                        && secondMap.containsKey(secondKey)
                        && Objects.equals(firstMap.get(firstKey), secondMap.get(firstKey))) {
                    temp.put("    " + firstKey + ": ", firstMap.get(firstKey) + "\n");
                }

            }
        }
        for (Map.Entry<String, Object> test : temp.entrySet()) {
            sb.append(test.getKey()).append(test.getValue());
        }
        sb.append("}");
        System.out.println(sb);
        return String.valueOf(sb);
    }

    public static String plainGenerate(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        final int substringForLinter = 10;
        StringBuilder sb = new StringBuilder();
        Map<String, Object> temp = new TreeMap<>(Comparator.comparing((String str) ->
                str.substring(substringForLinter))
                .thenComparingInt(str -> "Property".indexOf(str.charAt(2)))
        );

        for (String firstKey : firstMap.keySet()) {
            for (String secondKey : secondMap.keySet()) {
                if (secondMap.containsKey(secondKey) && !firstMap.containsKey(secondKey)) {
                    temp.put("Property '" + secondKey + "' was added with value: '", secondMap.get(secondKey) + "'\n");
                }
                if (firstMap.containsKey(firstKey) && !secondMap.containsKey(firstKey)) {
                    temp.put("Property '" + firstKey, "' was removed\n");
                }
                if (firstMap.containsKey(firstKey)
                        && secondMap.containsKey(firstKey)
                        && !Objects.equals(firstMap.get(firstKey), secondMap.get(firstKey))) {
                    temp.put("Property '"
                            + firstKey
                            + "' was updated. ",
                             "From "
                            + firstMap.get(firstKey)
                            + " to "
                            + secondMap.get(firstKey)
                            + "\n");
                }
            }
        }

        for (Map.Entry<String, Object> test : temp.entrySet()) {
            sb.append(test.getKey()).append(test.getValue());
        }

        System.out.println(sb);
        return String.valueOf(sb);
    }
}
