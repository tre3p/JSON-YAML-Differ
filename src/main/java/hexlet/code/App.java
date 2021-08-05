package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference."
        )

public final class App implements Callable<String> {
    @Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", defaultValue = "stylish")
    private String format;

    public static void main(String[] args) {
        final var commandLineRunner = new CommandLine(new App());
        commandLineRunner.execute(args);
    }

  @Override
  public String call() throws Exception {
        return Differ.generate(filepath1, filepath2, format);
    }
}
