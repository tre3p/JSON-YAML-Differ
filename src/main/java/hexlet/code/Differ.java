package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String firstFileToString = fileParse(filepath1);
        String secondFileToString = fileParse(filepath2);
        Map<String, Object> firstMap = Parser.parseToMap(firstFileToString, filepath1);
        Map<String, Object> secondMap = Parser.parseToMap(secondFileToString, filepath2);
        Map<String, String> defaultDiffMap = TreeAnalyzer.pullMapWithDefaultDiff(firstMap, secondMap);
        return Formatter.formatter(format, defaultDiffMap, firstMap, secondMap);
    }

    public static String fileParse(String path) throws IOException {
        Path resultPath = Paths.get(path).toAbsolutePath().normalize();
        return Files.readString(resultPath);
    }
}
