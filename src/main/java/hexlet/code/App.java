package hexlet.code;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
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
    try {
      Map<String, Object> firstMap = Parser.parsFile(filepath1);
      Map<String, Object> secondMap = Parser.parsFile(filepath2);
      Differ.generate(firstMap, secondMap);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
