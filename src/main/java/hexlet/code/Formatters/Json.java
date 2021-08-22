package hexlet.code.Formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Utils;

import java.util.*;

public class Json {
    public static String jsonGenerate(List<Map<String, Object>> diffList) throws JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> temp = editMapToJsonFormat(diffList);

        sb.append("{\n");
        Utils.pullStringBuilderWithValues(temp, sb);
        sb.append("}");
        return String.valueOf(sb).trim();
    }

    public static Map<String, Object> editMapToJsonFormat(List<Map<String, Object>> diffList) {
        return new HashMap<>();
    }
}
