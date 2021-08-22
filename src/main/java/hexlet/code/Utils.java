package hexlet.code;

import java.util.Map;

public class Utils {
    public static String pullStringBuilderWithValues(Map<String, Object> map, StringBuilder sb) {
        for (Map.Entry<String, Object> test : map.entrySet()) {
            sb.append(test.getKey()).append(test.getValue());
        }
        return sb.toString().trim();
    }
}
