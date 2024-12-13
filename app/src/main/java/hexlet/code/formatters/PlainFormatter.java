package hexlet.code.formatters;

import hexlet.code.DiffEntry;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlainFormatter {

    public static String format(List<DiffEntry> diff) {
        return diff.stream()
                .map(entry -> {
                    String key = entry.getKey();
                    switch (entry.getStatus()) {
                        case ADDED:
                            return String.format(
                                    "Property '%s' was added with value: %s",
                                    key, formatValue(entry.getNewValue())
                            );
                        case REMOVED:
                            return String.format("Property '%s' was removed", key);
                        case CHANGED:
                            return String.format(
                                    "Property '%s' was updated. From %s to %s",
                                    key, formatValue(entry.getOldValue()), formatValue(entry.getNewValue())
                            );
                        default:
                            return null; // Пропускаем UNCHANGED
                    }
                })
                .filter(line -> line != null)
                .collect(Collectors.joining("\n"));
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof List || value instanceof Map) {
            return "[complex value]";
        }
        return value.toString();
    }
}
