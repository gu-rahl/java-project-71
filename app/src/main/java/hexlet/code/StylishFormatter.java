package hexlet.code;

import java.util.List;

public class StylishFormatter {

    public static String format(List<DiffEntry> diff) {
        StringBuilder result = new StringBuilder("{\n");
        for (DiffEntry entry : diff) {
            switch (entry.getStatus()) {
                case ADDED -> result.append("  + ").append(entry.getKey()).append(": ").append(formatValue(entry.getNewValue())).append("\n");
                case REMOVED -> result.append("  - ").append(entry.getKey()).append(": ").append(formatValue(entry.getOldValue())).append("\n");
                case CHANGED -> {
                    result.append("  - ").append(entry.getKey()).append(": ").append(formatValue(entry.getOldValue())).append("\n");
                    result.append("  + ").append(entry.getKey()).append(": ").append(formatValue(entry.getNewValue())).append("\n");
                }
                case UNCHANGED -> result.append("    ").append(entry.getKey()).append(": ").append(formatValue(entry.getOldValue())).append("\n");
            }
        }
        result.append("}");
        return result.toString();
    }

    // Если значение — объект или массив, просто toString()
    private static String formatValue(Object value) {
        return value instanceof String ? value.toString() : String.valueOf(value);
    }
}
