package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Map;

public class AppTest {
    @Test
    public void differTestWithFlatJson() throws Exception {
        String firstJson = "/Users/noname/Desktop/java-project-lvl2/src/test/resources/file1.json";
        String secondJson = "/Users/noname/Desktop/java-project-lvl2/src/test/resources/file2.json";

        Map<String, Object> firstJsonMap = Parser.parsFile(firstJson);
        Map<String, Object> secondJsonMap = Parser.parsFile(secondJson);

        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        Assertions.assertEquals(Differ.generate(firstJsonMap, secondJsonMap), expected);
    }

    @Test
    public void differTestWithFlatYaml() throws Exception {
        String firstYaml = "/Users/noname/Desktop/java-project-lvl2/src/test/resources/file1.yaml";
        String secondYaml = "/Users/noname/Desktop/java-project-lvl2/src/test/resources/file2.yaml";

        Map<String, Object> firstYamlMap = Parser.parsFile(firstYaml);
        Map<String, Object> secondYamlMap = Parser.parsFile(secondYaml);

        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";

        Assertions.assertEquals(Differ.generate(firstYamlMap, secondYamlMap), expected);
    }
}
