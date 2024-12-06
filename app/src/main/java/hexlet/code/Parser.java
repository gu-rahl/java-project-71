package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    @SuppressWarnings("unchecked") // Подавляем предупреждение о "непроверенном приведении типов"
    public static Map<String, Object> parse(String filePath) throws Exception {
        // Читаем содержимое файла в строку
        String content = Files.readString(Paths.get(filePath));

        // Получаем подходящий ObjectMapper для обработки файла
        ObjectMapper mapper = getMapper(filePath);

        // Приводим результат к Map<String, Object>
        return (Map<String, Object>) mapper.readValue(content, Map.class);
    }

    private static ObjectMapper getMapper(String filePath) {
        // Если файл YAML, возвращаем ObjectMapper с YAMLFactory
        if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            return new ObjectMapper(new YAMLFactory());
        }
        // Для JSON используем стандартный ObjectMapper
        return new ObjectMapper();
    }
}
