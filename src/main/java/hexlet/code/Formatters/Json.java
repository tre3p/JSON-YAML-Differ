package hexlet.code.Formatters;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Json {
    public static String jsonGenerate(List<Map<String, Object>> diffList) {
        StringBuilder sb = new StringBuilder();
        List<Map<String, Object>> editedList = mapsFormatter(diffList);

        sb.append("{\n");
        String temp = editMapToJsonFormat(editedList);
        sb.append("}");
        return temp;
    }

    public static List<Map<String, Object>> mapsFormatter(List<Map<String, Object>> listOfMap) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        for (Map<String, Object> map : listOfMap) {
            for (Map.Entry<String, Object> firstEntrySet : map.entrySet()) {
                if (!Objects.equals(firstEntrySet.getValue(), "changed")
                        && !Objects.equals(firstEntrySet.getValue(), "added")
                        && !Objects.equals(firstEntrySet.getValue(), "unchanged")
                        && !Objects.equals(firstEntrySet.getValue(), "deleted")) {
                    if (firstEntrySet.getValue() instanceof String) {
                        map.put(firstEntrySet.getKey(), "\"" + map.get(firstEntrySet.getKey()) + "\"");
                    }
                    resultList.add(map);
                }
            }
        }
        return resultList;
    }

    public static String editMapToJsonFormat(List<Map<String, Object>> diffList) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (Map<String, Object> map : diffList) {
            for (Map.Entry<String, Object> diff : map.entrySet()) {
                if (Objects.equals(diff.getValue(), "changed")) {
                    sb.append("  {\n");
                    sb.append("   \"field\"" + ": " + "\"" + diff.getKey() + "\",\n")
                            .append("   \"status\"" + ": " + "\"changed\",\n")
                            .append("   \"oldValue\"" + ": " + map.get("oldValue") + ",\n")
                            .append("   \"newValue\"" + ": " + map.get("newValue") + "\n")
                            .append("  },\n");
                }
                if (Objects.equals(diff.getValue(), "added")) {
                    sb.append("  {\n");
                    sb.append("   \"field\"" + ": " + "\"" + diff.getKey() + "\",\n")
                            .append("   \"status\"" + ": " + "\"added\",\n")
                            .append("   \"oldValue\"" + ": " + map.get("oldValue") + ",\n")
                            .append("   \"newValue\"" + ": " + map.get("newValue") + "\n")
                            .append("  },\n");
                }
                if (Objects.equals(diff.getValue(), "unchanged")) {
                    sb.append("  {\n");
                    sb.append("   \"field\"" + ": " + "\"" + diff.getKey() + "\",\n")
                            .append("   \"status\"" + ": " + "\"unchanged\",\n")
                            .append("   \"oldValue\"" + ": " + map.get("value") + ",\n")
                            .append("   \"newValue\"" + ": " + map.get("value") + "\n")
                            .append("  },\n");
                }
                if (Objects.equals(diff.getValue(), "deleted")) {
                    sb.append("  {\n");
                    sb.append("   \"field\"" + ": " + "\"" + diff.getKey() + "\",\n")
                            .append("   \"status\"" + ": " + "\"deleted\",\n")
                            .append("   \"oldValue\"" + ": " + map.get("oldValue") + ",\n")
                            .append("   \"newValue\"" + ": " + map.get("newValue") + "\n")
                            .append("  },\n");
                }
            }
        }
        sb.append("}");
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }
}
