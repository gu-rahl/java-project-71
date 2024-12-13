package hexlet.code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Differ {

    public static List<DiffEntry> generate(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());
        List<DiffEntry> diff = new ArrayList<>();

        for (String key : allKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (!map1.containsKey(key)) {
                diff.add(new DiffEntry(key, null, value2, DiffEntry.Status.ADDED));
            } else if (!map2.containsKey(key)) {
                diff.add(new DiffEntry(key, value1, null, DiffEntry.Status.REMOVED));
            } else if (Objects.equals(value1, value2)) {
                diff.add(new DiffEntry(key, value1, value2, DiffEntry.Status.UNCHANGED));
            } else if (isMap(value1) && isMap(value2)) {
                @SuppressWarnings("unchecked")
                Map<String, Object> nestedMap1 = (Map<String, Object>) value1;
                @SuppressWarnings("unchecked")
                Map<String, Object> nestedMap2 = (Map<String, Object>) value2;
                List<DiffEntry> nestedDiff = generate(nestedMap1, nestedMap2);
                diff.add(new DiffEntry(key, nestedDiff, nestedDiff, DiffEntry.Status.CHANGED));
            } else {
                diff.add(new DiffEntry(key, value1, value2, DiffEntry.Status.CHANGED));
            }
        }
        return diff;
    }

    private static boolean isMap(Object value) {
        return value instanceof Map;
    }
}
