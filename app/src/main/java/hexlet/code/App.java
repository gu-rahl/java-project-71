package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true, // добавляет -h/--help и -V/--version
        version = "gendiff 1.0"
)
public class App implements Runnable {

    @Parameters(index = "0", description = "Path to the first file.")
    private String filePath1;

    @Parameters(index = "1", description = "Path to the second file.")
    private String filePath2;

    @Override
    public void run() {
        try {
            var diff = Differ.generate(filePath1, filePath2);
            // Форматер по умолчанию — stylish
            String result = hexlet.code.StylishFormatter.format(diff);
            System.out.println(result);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
