package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppTest {
    private final String source = "src/test/resources/";

    private Path pathForFlat = getPath(source + "expectedForStylishPlain");
    private final String expectedForFlat = Files.readString(pathForFlat);

    private Path pathToRecursive = getPath(source + "expectedForRecursive");
    private final String expectedForRecursive = Files.readString(pathToRecursive);

    private Path pathToPlain = getPath(source + "expectedForPlain");
    private final String expectedForPlainOutput = Files.readString(pathToPlain);

    private Path pathToJson = getPath(source + "expectedForJson.json");
    private final String expectedForJsonOutput = Files.readString(pathToJson);

    private Path pathToRecursiveJson = getPath(source + "expectedForRecursiveJson.json");
    private final String expectedForJsonRecursive = Files.readString(pathToRecursiveJson);

    public AppTest() throws IOException {
    }

    @Test
    void differTestWithFlatJson() throws Exception {
        String firstJson = "src/test/resources/fixtures/file1.json";
        String secondJson = "src/test/resources/fixtures/file2.json";

        assertEquals(expectedForFlat, Differ.generate(firstJson, secondJson));
    }

    @Test
    void differTestWithFlatYaml() throws Exception {
        String firstYaml = "src/test/resources/fixtures/file1.yaml";
        String secondYaml = "src/test/resources/fixtures/file2.yaml";

        assertEquals(expectedForFlat, Differ.generate(firstYaml, secondYaml));
    }

    @Test
    void differTestWithStylishOutput() throws Exception {
        String firstJson = "src/test/resources/fixtures/file1.json";
        String secondJson = "src/test/resources/fixtures/file2.json";

        assertEquals(expectedForFlat, Differ.generate(firstJson, secondJson, "stylish"));
    }

    @Test
    void differTestWithRecursiveJson() throws Exception {
        String firstJson = "src/test/resources/fixtures/firstRecursiveJson.json";
        String secondJson = "src/test/resources/fixtures/secondRecursiveJson.json";

        assertEquals(expectedForRecursive, Differ.generate(firstJson, secondJson));
    }

    @Test
    void differTestWithRecursiveYaml() throws Exception {
        String firstYaml = "src/test/resources/fixtures/firstRecursiveYaml.yaml";
        String secondYaml = "src/test/resources/fixtures/secondRecursiveYaml.yaml";

        assertEquals(expectedForRecursive, Differ.generate(firstYaml, secondYaml));
    }

    @Test
    void differTestWithRecursiveJsonAndPlainOutput() throws Exception {
        String firstJson = "src/test/resources/fixtures/firstRecursiveJson.json";
        String secondJson = "src/test/resources/fixtures/secondRecursiveJson.json";

        assertEquals(expectedForPlainOutput, Differ.generate(firstJson, secondJson, "plain"));
    }

    @Test
    void differTestWithJsonOutput() throws Exception {
        String firstJson = "src/test/resources/fixtures/file1.json";
        String secondJson = "src/test/resources/fixtures/file2.json";

        assertEquals(expectedForJsonOutput, Differ.generate(firstJson, secondJson, "json"));
    }

    @Test
    void differTestWithRecursiveJsonOutput() throws Exception {
        String firstJson = "src/test/resources/fixtures/firstRecursiveJson.json";
        String secondJson = "src/test/resources/fixtures/secondRecursiveJson.json";

        assertEquals(expectedForJsonRecursive, Differ.generate(firstJson, secondJson, "json"));
    }

    public static Path getPath(String path) {
        return Paths.get(path).toAbsolutePath().normalize();
    }
}
