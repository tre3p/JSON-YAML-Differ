package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppTest {

    public static Path getPath(String path) {
        return Paths.get(path).toAbsolutePath().normalize();
    }

    private Path pathForFlat = getPath("src/test/resources/expectedForStylishPlain");
    private final String expectedForFlat = Files.readString(pathForFlat);

    private Path pathToRecursive = getPath("src/test/resources/expectedForRecursive");
    private final String expectedForRecursive = Files.readString(pathToRecursive);

    private Path pathToPlain = getPath("src/test/resources/expectedForPlain");
    private final String expectedForPlainOutput = Files.readString(pathToPlain);



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
}
