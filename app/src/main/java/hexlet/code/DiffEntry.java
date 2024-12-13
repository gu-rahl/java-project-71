package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DiffEntry {

    public enum Status {
        ADDED, REMOVED, CHANGED, UNCHANGED
    }

    private final String key;
    private final Object oldValue;
    private final Object newValue;
    private final Status status;

    public DiffEntry(String key, Object oldValue, Object newValue, Status status) {
        this.key = key;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public Status getStatus() {
        return status;
    }

    public static List<DiffEntry> generateDiff(Map<String, Object> map1, Map<String, Object> map2) {
        List<String> allKeys = new ArrayList<>(map1.keySet());
        allKeys.addAll(map2.keySet());
        allKeys = allKeys.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        List<DiffEntry> diff = new ArrayList<>();

        for (String key : allKeys) {
            if (!map1.containsKey(key)) {
                diff.add(new DiffEntry(key, null, map2.get(key), Status.ADDED));
            } else if (!map2.containsKey(key)) {
                diff.add(new DiffEntry(key, map1.get(key), null, Status.REMOVED));
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                diff.add(new DiffEntry(key, map1.get(key), map2.get(key), Status.CHANGED));
            } else {
                diff.add(new DiffEntry(key, map1.get(key), map2.get(key), Status.UNCHANGED));
            }
        }

        return diff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiffEntry diffEntry = (DiffEntry) o;
        return Objects.equals(key, diffEntry.key)
                && Objects.equals(oldValue, diffEntry.oldValue)
                && Objects.equals(newValue, diffEntry.newValue)
                && status == diffEntry.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, oldValue, newValue, status);
    }
}
