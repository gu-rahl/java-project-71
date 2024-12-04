package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true, // Добавляет флаги -h/--help и -V/--version
        version = "gendiff 1.0"
)
public class App implements Runnable {

    @Option(
            names = {"-f", "--format"},
            description = "output format [default: stylish]",
            defaultValue = "stylish"
    )
    private String format;

    @Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        System.out.printf(
                "Comparing files: %s and %s with format: %s%n",
                filepath1,
                filepath2,
                format
        );
    }
}
