package hexlet.code.Formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.List;

public class Json {
    public static String jsonGenerate(List<Map<String, Object>> diffList) throws JsonProcessingException {
        return stringFormatter(diffList);
    }

    private static String stringFormatter(List<Map<String, Object>> diffList) throws JsonProcessingException {
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
