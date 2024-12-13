package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                """.trim();

        assertEquals(expected, PlainFormatter.format(diff));
    }
}
