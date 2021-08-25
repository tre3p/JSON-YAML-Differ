package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String firstFileToString = readFileContent(filepath1);
        String secondFileToString = readFileContent(filepath2);
        Map<String, Object> firstMap = Parser.parseToMap(firstFileToString, filepath1);
        Map<String, Object> secondMap = Parser.parseToMap(secondFileToString, filepath2);
        List<Map<String, Object>> defaultDiffList = TreeAnalyzer.analyzeDiff(firstMap, secondMap);
        return Formatter.formatDiff(format, defaultDiffList);
    }

    public static String readFileContent(String path) throws IOException {
        Path resultPath = Paths.get(path).toAbsolutePath().normalize();
        return Files.readString(resultPath);
    }
}
