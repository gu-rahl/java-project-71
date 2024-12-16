package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formatters.JsonFormatter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Тесты для форматтера JSON
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

        JsonNode expected = objectMapper.readTree(expectedJson);
        JsonNode actual = objectMapper.readTree(JsonFormatter.format(diff));

        assertEquals(expected, actual, "JSON вывод не совпадает с ожидаемым результатом");
    }
}
