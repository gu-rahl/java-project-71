package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Тесты для форматтера Plain
class PlainFormatterTest {
    @Test
    void testPlainFormat() {
        List<DiffEntry> diff = List.of(
                new DiffEntry("chars2", List.of("d", "e", "f"), false, DiffEntry.Status.CHANGED),
                new DiffEntry("checked", false, true, DiffEntry.Status.CHANGED),
                new DiffEntry("key1", "value1", null, DiffEntry.Status.REMOVED),
                new DiffEntry("key2", null, "value2", DiffEntry.Status.ADDED)
        );

        String expected = """
                Свойство 'chars2' было обновлено. С [complex value] на false
                Свойство 'checked' было обновлено. С false на true
                Свойство 'key1' было удалено
                Свойство 'key2' было добавлено со значением: 'value2'
                """.trim();

        assertEquals(expected, PlainFormatter.format(diff));
    }
}
