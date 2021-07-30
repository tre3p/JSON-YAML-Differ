package hexlet.code;


import com.fasterxml.jackson.core.type.TypeReference;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference."
        )

public final class App implements Runnable {
  @Parameters(index = "0", description = "path to first file")
  private String filepath1;

  @Parameters(index = "1", description = "path to second file")
  private String filepath2;

  @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
  private String format;


  public static void main(String[] args) {
    CommandLine.run(new App(), args);
  }

  @Override
  public void run() {
    ObjectMapper firstObjectMapper = new ObjectMapper();
    ObjectMapper secondObjectMapper = new ObjectMapper();

    try {
      Map<String, Object> firstMap = firstObjectMapper.readValue(new File(filepath1),
              new TypeReference<Map<String, Object>>() { });
      Map<String, Object> secondMap = secondObjectMapper.readValue(new File(filepath2),
              new TypeReference<Map<String, Object>>() { });
      Differ.generate(firstMap, secondMap);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
