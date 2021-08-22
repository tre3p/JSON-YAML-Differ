package hexlet.code.Formatters;

import hexlet.code.Utils;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class Json {
    public static String jsonGenerate(List<Map<String, Object>> diffList) {
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
