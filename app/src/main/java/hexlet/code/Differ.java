package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Differ {

    public static List<DiffEntry> generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> map1 = Parser.parse(filePath1);
        Map<String, Object> map2 = Parser.parse(filePath2);
        return generate(map1, map2);
    }

    public static List<DiffEntry> generate(Map<String, Object> map1, Map<String, Object> map2) {
        List<DiffEntry> diff = new ArrayList<>();
        var allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        for (var key : allKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (!map1.containsKey(key)) {
                // ключ добавлен
                diff.add(new DiffEntry(key, null, value2, DiffEntry.Status.ADDED));
            } else if (!map2.containsKey(key)) {
                // ключ удалён
                diff.add(new DiffEntry(key, value1, null, DiffEntry.Status.REMOVED));
            } else if (!value1.equals(value2)) {
                // значение изменено
                diff.add(new DiffEntry(key, value1, value2, DiffEntry.Status.CHANGED));
            } else {
                // значение не изменилось
                diff.add(new DiffEntry(key, value1, value2, DiffEntry.Status.UNCHANGED));
            }
        }

        return diff;
    }
}
