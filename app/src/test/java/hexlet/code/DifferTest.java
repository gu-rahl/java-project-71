package hexlet.code;

import hexlet.code.formatters.StylishFormatter;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DifferTest {

    @Test
    void testNestedStructures() throws Exception {
        // Проверяем существование файлов
        Path filePath1 = Path.of("src/test/resources/nested1.json");
        Path filePath2 = Path.of("src/test/resources/nested2.json");

        assertTrue(Files.exists(filePath1), "Файл nested1.json не найден!");
        assertTrue(Files.exists(filePath2), "Файл nested2.json не найден!");

        // Парсим файлы
        Map<String, Object> map1 = Parser.parse(filePath1.toString());
        Map<String, Object> map2 = Parser.parse(filePath2.toString());

        // Генерируем разницу
        List<DiffEntry> actualDiff = DiffEntry.generateDiff(map1, map2);

        // Ожидаемый результат (список объектов DiffEntry)
        List<DiffEntry> expectedDiff = List.of(
                new DiffEntry("chars1", List.of("a", "b", "c"), List.of("a", "b", "c"), DiffEntry.Status.UNCHANGED),
                new DiffEntry("chars2", List.of("d", "e", "f"), false, DiffEntry.Status.CHANGED),
                new DiffEntry("checked", false, true, DiffEntry.Status.CHANGED),
                new DiffEntry("default", null, List.of("value1", "value2"), DiffEntry.Status.CHANGED),
                new DiffEntry("id", 45, null, DiffEntry.Status.CHANGED),
                new DiffEntry("key1", "value1", null, DiffEntry.Status.REMOVED),
                new DiffEntry("key2", null, "value2", DiffEntry.Status.ADDED),
                new DiffEntry("numbers1", List.of(1, 2, 3, 4), List.of(1, 2, 3, 4), DiffEntry.Status.UNCHANGED),
                new DiffEntry("numbers2", List.of(2, 3, 4, 5), List.of(22, 33, 44, 55), DiffEntry.Status.CHANGED),
                new DiffEntry("numbers3", List.of(3, 4, 5), null, DiffEntry.Status.REMOVED),
                new DiffEntry("numbers4", null, List.of(4, 5, 6), DiffEntry.Status.ADDED),
                new DiffEntry("obj1", null, Map.of("nestedKey", "value", "isNested", true), DiffEntry.Status.ADDED),
                new DiffEntry("setting1", "Some value", "Another value", DiffEntry.Status.CHANGED),
                new DiffEntry("setting2", 200, 300, DiffEntry.Status.CHANGED),
                new DiffEntry("setting3", true, "none", DiffEntry.Status.CHANGED)
        );

        // Сравнение списков
        assertEquals(expectedDiff, actualDiff, "Разница между файлами не совпадает с ожиданиями!");

        // Форматирование
        String actualFormatted = StylishFormatter.format(actualDiff).trim();
        String expectedFormatted = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }
                """.trim();

        System.out.println("Actual formatted result:");
        System.out.println(actualFormatted);
        System.out.println("Expected formatted result:");
        System.out.println(expectedFormatted);

        assertEquals(expectedFormatted, actualFormatted, "Результат форматирования не совпадает с ожиданиями!");
    }
}
