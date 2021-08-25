package hexlet.code.Formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.Objects;
import java.util.LinkedHashMap;

public class Json {
    public static String jsonGenerate(List<Map<String, Object>> diffList) throws JsonProcessingException {
        List<Map<String, Object>> result = editMapToJsonFormat(diffList);
        return stringFormatter(result);
    }


    public static List<Map<String, Object>> editMapToJsonFormat(List<Map<String, Object>> diffList) {
        List<Map<String, Object>> result = new LinkedList<>();
        for (Map<String, Object> map : diffList) {
            for (Map.Entry<String, Object> diff : map.entrySet()) {
                Map<String, Object> temp = new LinkedHashMap<>();
                if (Objects.equals(diff.getValue(), "changed")) {
                    temp.putAll(addValueToMap(diff.getKey()
                            ,"changed"
                            ,map.get("oldValue")
                            ,map.get("newValue")));
                }
                if (Objects.equals(diff.getValue(), "added")) {
                    temp.putAll(addValueToMap(diff.getKey()
                            ,"added"
                            ,map.get("newValue")
                            ,map.get("newValue")));
                }
                if (Objects.equals(diff.getValue(), "unchanged")) {
                    temp.putAll(addValueToMap(diff.getKey()
                            ,"unchanged"
                            ,map.get("oldValue")
                            ,map.get("oldValue")));

                }
                if (Objects.equals(diff.getValue(), "deleted")) {
                    temp.putAll(addValueToMap(diff.getKey()
                            ,"deleted"
                            ,map.get("oldValue")
                            ,map.get("oldValue")));
                }
                if (temp.size() != 0) {
                    result.add(temp);
                }
            }
        }
        return result;
    }

    public static Map<String, Object> addValueToMap(String key, String status, Object oldValue, Object newValue) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("field", key);
        result.put("status", status);
        result.put("oldValue", oldValue);
        result.put("newValue", newValue);
        return result;
    }

    public static String stringFormatter(List<Map<String, Object>> diffList) throws JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        ObjectMapper oj = new ObjectMapper();
        sb.append("{\n  \"diffs\"" + ": [\n");

        for (Map map : diffList) {
            sb.append("    " + oj.writeValueAsString(map) + ",\n");
        }
        sb.append("  ]\n}");
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }
}
