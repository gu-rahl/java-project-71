package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        // Чтение и парсинг JSON-файлов
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map1 = objectMapper.readValue(Files.readString(Paths.get(filePath1)), Map.class);
        Map<String, Object> map2 = objectMapper.readValue(Files.readString(Paths.get(filePath2)), Map.class);

        // Используем TreeMap для сортировки ключей
        Map<String, String> result = new TreeMap<>();

        // Обрабатываем ключи из первого файла
        for (String key : map1.keySet()) {
            if (!map2.containsKey(key)) {
                result.put("- " + key, String.valueOf(map1.get(key))); // Удалённые ключи
            } else if (!map1.get(key).equals(map2.get(key))) {
                result.put("- " + key, String.valueOf(map1.get(key))); // Старое значение
                result.put("+ " + key, String.valueOf(map2.get(key))); // Новое значение
            } else {
                result.put("  " + key, String.valueOf(map1.get(key))); // Неизменённое значение
            }
        }

        // Обрабатываем ключи, которые есть только во втором файле
        for (String key : map2.keySet()) {
            if (!map1.containsKey(key)) {
                result.put("+ " + key, String.valueOf(map2.get(key))); // Добавленные ключи
            }
        }

        // Формируем строку результата
        StringBuilder diff = new StringBuilder("{\n");
        for (var entry : result.entrySet()) {
            diff.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        diff.append("}");
        return diff.toString();
    }
}
