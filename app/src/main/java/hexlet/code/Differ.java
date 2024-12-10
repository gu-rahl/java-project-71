package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Differ {

    public static List<DiffEntry> generate(Map<String, Object> map1, Map<String, Object> map2) {
        List<DiffEntry> diff = new ArrayList<>();

        try {
            for (String key : map1.keySet()) {
                Object value1 = map1.get(key);
                Object value2 = map2.get(key);

                if (!map2.containsKey(key)) {
                    diff.add(new DiffEntry(key, value1, null, DiffEntry.Status.REMOVED));
                } else if (value1 == null && value2 != null || value1 != null && !value1.equals(value2)) {
                    diff.add(new DiffEntry(key, value1, value2, DiffEntry.Status.CHANGED));
                } else {
                    diff.add(new DiffEntry(key, value1, value2, DiffEntry.Status.UNCHANGED));
                }
            }

            for (String key : map2.keySet()) {
                if (!map1.containsKey(key)) {
                    diff.add(new DiffEntry(key, null, map2.get(key), DiffEntry.Status.ADDED));
                }
            }
        } catch (Exception e) {
            System.err.println("Error while generating diff: " + e.getMessage());
            e.printStackTrace();
        }

        return diff;
    }

}
