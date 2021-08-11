package hexlet.code.Formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Json {
    public static String jsonGenerate(Map<String, String> keys,
                                      Map<String, Object> firstMap,
                                      Map<String, Object> secondMap) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(Stylish.stylishGenerate(keys, firstMap, secondMap));
    }
}
