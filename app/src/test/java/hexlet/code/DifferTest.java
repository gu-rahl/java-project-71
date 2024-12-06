package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    @Test
    void testYamlFilesComparison() throws Exception {
        // Пути к тестовым файлам
        String filePath1 = "src/test/resources/file1.yml";
        String filePath2 = "src/test/resources/file2.yml";

        // Ожидаемый результат
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }
                """;

        // Получаем фактический результат
        String actual = Differ.generate(filePath1, filePath2);

        // Нормализуем строки (удаляем лишние пробелы, сортируем строки)
        String normalizedExpected = normalizeAndSort(expected);
        String normalizedActual = normalizeAndSort(actual);

        // Проверяем результат
        assertEquals(normalizedExpected, normalizedActual);
    }

    // Метод для нормализации и сортировки строк
    private String normalizeAndSort(String input) {
        return Arrays.stream(input.trim().split("\n"))
                .map(String::trim)
                .sorted()
                .collect(Collectors.joining("\n"));
    }
}
