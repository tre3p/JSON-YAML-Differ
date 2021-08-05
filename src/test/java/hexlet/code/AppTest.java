package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AppTest {
    private final String expectedForFlat = "{\n"
            + "  - follow: false\n"
            + "    host: hexlet.io\n"
            + "  - proxy: 123.234.53.22\n"
            + "  - timeout: 50\n"
            + "  + timeout: 20\n"
            + "  + verbose: true\n"
            + "}";

    private final String expectedForRecursive = "{\n"
            + "    chars1: [a, b, c]\n"
            + "  - chars2: [d, e, f]\n"
            + "  + chars2: false\n"
            + "  - checked: false\n"
            + "  + checked: true\n"
            + "  - default: null\n"
            + "  + default: [value1, value2]\n"
            + "  - id: 45\n"
            + "  + id: null\n"
            + "  - key1: value1\n"
            + "  + key2: value2\n"
            + "    numbers1: [1, 2, 3, 4]\n"
            + "  - numbers2: [2, 3, 4, 5]\n"
            + "  + numbers2: [22, 33, 44, 55]\n"
            + "  - numbers3: [3, 4, 5]\n"
            + "  + numbers4: [4, 5, 6]\n"
            + "  + obj1: {nestedKey=value, isNested=true}\n"
            + "  - setting1: Some value\n"
            + "  + setting1: Another value\n"
            + "  - setting2: 200\n"
            + "  + setting2: 300\n"
            + "  - setting3: true\n"
            + "  + setting3: none\n"
            + "}";

    private final String expectedForPlainOutput = "Property 'chars2' was updated. From [complex value] to false\n"
            + "Property 'checked' was updated. From false to true\n"
            + "Property 'default' was updated. From null to [complex value]\n"
            + "Property 'id' was updated. From 45 to null\n"
            + "Property 'key1' was removed\n"
            + "Property 'key2' was added with value: 'value2'\n"
            + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
            + "Property 'numbers3' was removed\n"
            + "Property 'numbers4' was added with value: [complex value]\n"
            + "Property 'obj1' was added with value: [complex value]\n"
            + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
            + "Property 'setting2' was updated. From 200 to 300\n"
            + "Property 'setting3' was updated. From true to 'none'";

    @Test
    void differTestWithFlatJson() throws Exception {
        String firstJson = "src/test/resources/file1.json";
        String secondJson = "src/test/resources/file2.json";

        assertEquals(expectedForFlat, Differ.generate(firstJson, secondJson, "stylish"));
    }

    @Test
    void differTestWithFlatYaml() throws Exception {
        String firstYaml = "src/test/resources/file1.yaml";
        String secondYaml = "src/test/resources/file2.yaml";

        assertEquals(expectedForFlat, Differ.generate(firstYaml, secondYaml));
    }

    @Test
    void differTestWithRecursiveJson() throws Exception {
        String firstJson = "src/test/resources/firstRecursiveJson.json";
        String secondJson = "src/test/resources/secondRecursiveJson.json";

        assertEquals(expectedForRecursive, Differ.generate(firstJson, secondJson, "stylish"));
    }

    @Test
    void differTestWithRecursiveYaml() throws Exception {
        String firstYaml = "src/test/resources/firstRecursiveYaml.yaml";
        String secondYaml = "src/test/resources/secondRecursiveYaml.yaml";

        assertEquals(expectedForRecursive, Differ.generate(firstYaml, secondYaml, "stylish"));
    }

    @Test
    void differTestWithRecursiveJsonAndPlainOutput() throws Exception {
        String firstJson = "src/test/resources/firstRecursiveJson.json";
        String secondJson = "src/test/resources/secondRecursiveJson.json";

        assertEquals(expectedForPlainOutput, Differ.generate(firstJson, secondJson, "plain"));
    }
}
