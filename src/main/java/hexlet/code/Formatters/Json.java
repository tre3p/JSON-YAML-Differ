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
                    temp.put("field", diff.getKey());
                    temp.put("status", "changed");
                    temp.put("oldValue", map.get("oldValue"));
                    temp.put("newValue", map.get("newValue"));
                }
                if (Objects.equals(diff.getValue(), "added")) {
                    temp.put("field", diff.getKey());
                    temp.put("status", "added");
                    temp.put("newValue", map.get("newValue"));
                }
                if (Objects.equals(diff.getValue(), "unchanged")) {
                    temp.put("field", diff.getKey());
                    temp.put("status", "unchanged");
                    temp.put("oldValue", map.get("value"));
                    temp.put("newValue", map.get("value"));
                }
                if (Objects.equals(diff.getValue(), "deleted")) {
                    temp.put("field", diff.getKey());
                    temp.put("status", "deleted");
                    temp.put("oldValue", map.get("oldValue"));
                }
                if (temp.size() != 0) {
                    result.add(temp);
                }
            }
        }
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
