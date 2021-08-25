package hexlet.code;

import java.util.Map;

public class Utils {
    public static String pullStringBuilderWithValues(Map<String, Object> map) {
        String result = "";
        for (Map.Entry<String, Object> test : map.entrySet()) {
            result += test.getKey() + test.getValue();
        }
        return result;
    }
}
