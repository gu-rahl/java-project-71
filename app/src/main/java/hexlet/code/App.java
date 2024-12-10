package hexlet.code;

import hexlet.code.formatters.StylishFormatter;
import picocli.CommandLine;

import java.util.Map;

@CommandLine.Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)
public class App implements Runnable {

    @CommandLine.Parameters(index = "0", description = "Path to the first file.")
    private String filePath1;

    @CommandLine.Parameters(index = "1", description = "Path to the second file.")
    private String filePath2;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        try {
            // Проверка наличия файлов
            System.out.println("Reading files:");
            System.out.println("File 1: " + filePath1);
            System.out.println("File 2: " + filePath2);

            // Парсим файлы
            Map<String, Object> map1 = Parser.parse(filePath1);
            Map<String, Object> map2 = Parser.parse(filePath2);

            // Генерируем различия
            var diff = Differ.generate(map1, map2);

            // Форматируем результат
            String formattedResult = StylishFormatter.format(diff);

            // Выводим результат
            System.out.println("Comparison result:");
            System.out.println(formattedResult);
        } catch (Exception e) {
            System.err.println("Error occurred while executing the program:");
            e.printStackTrace();
        }
    }
}
