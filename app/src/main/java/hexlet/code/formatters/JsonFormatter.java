package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffEntry;

import java.util.List;

public class JsonFormatter {

    public static String format(List<DiffEntry> diff) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(diff);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while serializing diff to JSON", e);
        }
    }
}
