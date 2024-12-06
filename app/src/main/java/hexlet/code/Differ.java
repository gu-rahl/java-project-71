package hexlet.code;

import java.util.Map;
import java.util.TreeMap;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> map1 = Parser.parse(filePath1);
        Map<String, Object> map2 = Parser.parse(filePath2);

        Map<String, String> result = new TreeMap<>();

        for (String key : map1.keySet()) {
            if (!map2.containsKey(key)) {
                result.put("- " + key, String.valueOf(map1.get(key)));
            } else if (!map1.get(key).equals(map2.get(key))) {
                result.put("- " + key, String.valueOf(map1.get(key)));
                result.put("+ " + key, String.valueOf(map2.get(key)));
            } else {
                result.put("  " + key, String.valueOf(map1.get(key)));
            }
        }

        for (String key : map2.keySet()) {
            if (!map1.containsKey(key)) {
                result.put("+ " + key, String.valueOf(map2.get(key)));
            }
        }

        StringBuilder diff = new StringBuilder("{\n");
        for (var entry : result.entrySet()) {
            diff.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        diff.append("}");
        return diff.toString().trim(); // Убираем лишние пробелы
    }
}
