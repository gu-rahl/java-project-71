package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String filePath) throws Exception {
        String content = Files.readString(Paths.get(filePath));
        System.out.println("Parsing file: " + filePath);
        System.out.println("Content: " + content);
        ObjectMapper mapper = getMapper(filePath);
        @SuppressWarnings("unchecked")
        Map<String, Object> result = mapper.readValue(content, Map.class);
        return result;
    }


    private static ObjectMapper getMapper(String filePath) {
        if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            return new ObjectMapper(new YAMLFactory());
        }
        return new ObjectMapper(); // JSON по умолчанию
    }
}
