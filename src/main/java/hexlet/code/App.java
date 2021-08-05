package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

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

  @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", defaultValue = "stylish")
  private String format;

  public static void main(String[] args) {
    CommandLine.run(new App(), args);
  }

  @Override
  public void run() {
    try {
      Differ.generate(filepath1, filepath2);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
