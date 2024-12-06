package hexlet.code;

import java.util.Map;
import java.util.TreeMap;

public class Differ {

    /**
     * Сравнивает два файла и возвращает строку с различиями.
     * @param filePath1 Путь к первому файлу.
     * @param filePath2 Путь ко второму файлу.
     * @return Различия между файлами в виде строки.
     * @throws Exception Если возникла ошибка при обработке файлов.
     */
    public static String generate(String filePath1, String filePath2) throws Exception {
        // Читаем содержимое файлов в виде карт
        Map<String, Object> map1 = Parser.parse(filePath1);
        Map<String, Object> map2 = Parser.parse(filePath2);

        // Для хранения и сортировки результатов
        Map<String, String> result = new TreeMap<>();

        // Проверяем ключи из первого файла
        for (String key : map1.keySet()) {
            if (!map2.containsKey(key)) {
                // Ключ есть только в первом файле
                result.put("- " + key, String.valueOf(map1.get(key)));
            } else if (!map1.get(key).equals(map2.get(key))) {
                // Значение ключа изменилось
                result.put("- " + key, String.valueOf(map1.get(key)));
                result.put("+ " + key, String.valueOf(map2.get(key)));
            } else {
                // Значение ключа осталось таким же
                result.put("  " + key, String.valueOf(map1.get(key)));
            }
        }

        // Проверяем ключи, которые есть только во втором файле
        for (String key : map2.keySet()) {
            if (!map1.containsKey(key)) {
                result.put("+ " + key, String.valueOf(map2.get(key)));
            }
        }

        // Собираем результат в строку
        StringBuilder diff = new StringBuilder("{\n");
        for (var entry : result.entrySet()) {
            diff.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        diff.append("}");

        // Возвращаем строку без лишних пробелов
        return diff.toString().trim();
    }
}
