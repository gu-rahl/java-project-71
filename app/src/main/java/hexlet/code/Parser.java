package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

// Класс для парсинга JSON и YAML файлов
public class Parser {
    public static Map<String, Object> parse(String filePath) throws Exception {
        String content = Files.readString(Path.of(filePath));
        ObjectMapper mapper = filePath.endsWith(".yaml") || filePath.endsWith(".yml")
                ? new ObjectMapper(new YAMLFactory())
                : new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }
}
