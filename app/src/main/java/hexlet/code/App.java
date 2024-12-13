package hexlet.code;

import hexlet.code.formatters.StylishFormatter;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Parameters(index = "0", description = "Path to the first file.")
    private String filePath1;

    @Parameters(index = "1", description = "Path to the second file.")
    private String filePath2;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        try {
            System.out.println("Reading files:");
            System.out.println("File 1: " + filePath1);
            System.out.println("File 2: " + filePath2);

            // Проверяем существование файлов
            if (!Files.exists(Path.of(filePath1)) || !Files.exists(Path.of(filePath2))) {
                throw new IllegalArgumentException("One or both files do not exist.");
            }

            // Парсим файлы в Map
            Map<String, Object> map1 = Parser.parse(filePath1);
            Map<String, Object> map2 = Parser.parse(filePath2);

            // Проверяем, что файлы корректно распарсились
            if (map1 == null || map2 == null) {
                throw new IllegalArgumentException("Failed to parse one or both files.");
            }

            // Генерируем различия
            var diff = Differ.generate(map1, map2);

            // Форматируем результат
            String result = StylishFormatter.format(diff);
            System.out.println("Comparison result:");
            System.out.println(result);
        } catch (Exception e) {
            System.err.println("Error occurred while executing the program:");
            e.printStackTrace();
        }
    }
}
