package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffEntry;

import java.util.List;

// Форматтер для вывода в формате JSON
public class JsonFormatter {
    public static String format(List<DiffEntry> diff) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(diff);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при сериализации diff в JSON", e);
        }
    }
}
