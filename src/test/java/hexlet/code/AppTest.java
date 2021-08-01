package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.Map;

public class AppTest {
    private final String expectedForFlat = "{\n"
            + "  - follow: false\n"
            + "    host: hexlet.io\n"
            + "  - proxy: 123.234.53.22\n"
            + "  - timeout: 50\n"
            + "  + timeout: 20\n"
            + "  + verbose: true\n"
            + "}";

    private final String expectedForRecursive = "{\n" +
            "    chars1: [a, b, c]\n" +
            "  - chars2: [d, e, f]\n" +
            "  + chars2: false\n" +
            "  - checked: false\n" +
            "  + checked: true\n" +
            "  - default: null\n" +
            "  + default: [value1, value2]\n" +
            "  - id: 45\n" +
            "  + id: null\n" +
            "  - key1: value1\n" +
            "  + key2: value2\n" +
            "    numbers1: [1, 2, 3, 4]\n" +
            "  - numbers2: [2, 3, 4, 5]\n" +
            "  + numbers2: [22, 33, 44, 55]\n" +
            "  - numbers3: [3, 4, 5]\n" +
            "  + numbers4: [4, 5, 6]\n" +
            "  + obj1: {nestedKey=value, isNested=true}\n" +
            "  - setting1: Some value\n" +
            "  + setting1: Another value\n" +
            "  - setting2: 200\n" +
            "  + setting2: 300\n" +
            "  - setting3: true\n" +
            "  + setting3: none\n" +
            "}";

    @Test
    void differTestWithFlatJson() throws Exception {
        String firstJson = "src/test/resources/file1.json";
        String secondJson = "src/test/resources/file2.json";

        Map<String, Object> firstJsonMap = Parser.parsFile(firstJson);
        Map<String, Object> secondJsonMap = Parser.parsFile(secondJson);

        assertEquals(expectedForFlat, Differ.generate(firstJsonMap, secondJsonMap));
    }

    @Test
    void differTestWithFlatYaml() throws Exception {
        String firstYaml = "src/test/resources/file1.yaml";
        String secondYaml = "src/test/resources/file2.yaml";

        Map<String, Object> firstYamlMap = Parser.parsFile(firstYaml);
        Map<String, Object> secondYamlMap = Parser.parsFile(secondYaml);

        assertEquals(expectedForFlat, Differ.generate(firstYamlMap, secondYamlMap));
    }

    @Test
    void differTestWithRecursiveJson() throws Exception {
        String firstJson = "src/test/resources/firstRecursiveJson.json";
        String secondJson = "src/test/resources/secondRecursiveJson.json";

        Map<String, Object> firstRecursiveMap = Parser.parsFile(firstJson);
        Map<String, Object> secondRecursiveMap = Parser.parsFile(secondJson);

        assertEquals(expectedForRecursive, Differ.generate(firstRecursiveMap, secondRecursiveMap));
    }

    @Test
    void differTestWithRecursiveYaml() throws Exception {
        String firstYaml = "src/test/resources/firstRecursiveYaml.yaml";
        String secondYaml = "src/test/resources/secondRecursiveYaml";

        Map<String, Object> firstRecursiveMap = Parser.parsFile(firstYaml);
        Map<String, Object> secondRecursiveMap = Parser.parsFile(secondYaml);

        assertEquals(expectedForRecursive, Differ.generate(firstRecursiveMap, secondRecursiveMap));
    }
}
