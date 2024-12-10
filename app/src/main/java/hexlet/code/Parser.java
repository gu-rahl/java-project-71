package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String filePath) throws Exception {
        String content = Files.readString(Paths.get(filePath));
        ObjectMapper mapper = getMapper(filePath);
        @SuppressWarnings("unchecked")
        Map<String, Object> result = mapper.readValue(content, Map.class);
        if (result == null) {
            throw new IllegalArgumentException("Failed to parse file: " + filePath);
        }
        return result;
    }

    private static ObjectMapper getMapper(String filePath) {
        if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            return new ObjectMapper(new YAMLFactory());
        }
        return new ObjectMapper();
    }
}
