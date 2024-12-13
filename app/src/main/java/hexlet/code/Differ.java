package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        Map<String, Object> map1 = parse(filePath1);
        Map<String, Object> map2 = parse(filePath2);

        var diff = DiffEntry.generateDiff(map1, map2);

        return Formatter.format(diff, formatName);
    }

    private static Map<String, Object> parse(String filePath) throws Exception {
        String content = Files.readString(Path.of(filePath));
        ObjectMapper mapper = filePath.endsWith(".yaml") || filePath.endsWith(".yml")
                ? new ObjectMapper(new YAMLFactory())
                : new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }
}
