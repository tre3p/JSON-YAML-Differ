package hexlet.code.Formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Json {
    public static String jsonGenerate(Map<String, String> keys,
                                      Map<String, Object> firstMap,
                                      Map<String, Object> secondMap) throws JsonProcessingException {
        String result;
        ObjectMapper objectMapper = new ObjectMapper();
        result = objectMapper.writeValueAsString(Stylish.stylishGenerate(keys, firstMap, secondMap));
        return result;
    }
}
