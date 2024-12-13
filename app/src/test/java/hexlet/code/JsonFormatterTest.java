package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import hexlet.code.formatters.JsonFormatter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonFormatterTest {

    @Test
    void testJsonFormat() throws Exception {
        List<DiffEntry> diff = List.of(
                new DiffEntry("chars2", List.of("d", "e", "f"), false, DiffEntry.Status.CHANGED),
                new DiffEntry("checked", false, true, DiffEntry.Status.CHANGED),
                new DiffEntry("key1", "value1", null, DiffEntry.Status.REMOVED),
                new DiffEntry("key2", null, "value2", DiffEntry.Status.ADDED)
        );

        ObjectMapper objectMapper = new ObjectMapper();

        // Ожидаемый JSON в виде строки
        String expectedJson = """
                [
                  {
                    "key": "chars2",
                    "oldValue": ["d", "e", "f"],
                    "newValue": false,
                    "status": "CHANGED"
                  },
                  {
                    "key": "checked",
                    "oldValue": false,
                    "newValue": true,
                    "status": "CHANGED"
                  },
                  {
                    "key": "key1",
                    "oldValue": "value1",
                    "newValue": null,
                    "status": "REMOVED"
                  },
                  {
                    "key": "key2",
                    "oldValue": null,
                    "newValue": "value2",
                    "status": "ADDED"
                  }
                ]
                """.trim();

        // Десериализация JSON в объекты для сравнения
        JsonNode expected = objectMapper.readTree(expectedJson);
        JsonNode actual = objectMapper.readTree(JsonFormatter.format(diff));

        // Сравнение объектов JSON
        assertEquals(expected, actual, "JSON output does not match the expected result");
    }
}
