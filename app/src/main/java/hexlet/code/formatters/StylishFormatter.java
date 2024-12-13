package hexlet.code.formatters;

import hexlet.code.DiffEntry;

import java.util.List;
import java.util.Map;

public class StylishFormatter {

    public static String format(List<DiffEntry> diff) {
        StringBuilder result = new StringBuilder("{\n");
        for (DiffEntry entry : diff) {
            switch (entry.getStatus()) {
                case ADDED -> result.append("  + ").append(entry.getKey())
                        .append(": ").append(formatValue(entry.getNewValue())).append("\n");
                case REMOVED -> result.append("  - ").append(entry.getKey())
                        .append(": ").append(formatValue(entry.getOldValue())).append("\n");
                case CHANGED -> {
                    result.append("  - ").append(entry.getKey())
                            .append(": ").append(formatValue(entry.getOldValue())).append("\n");
                    result.append("  + ").append(entry.getKey())
                            .append(": ").append(formatValue(entry.getNewValue())).append("\n");
                }
                case UNCHANGED -> result.append("    ").append(entry.getKey())
                        .append(": ").append(formatValue(entry.getOldValue())).append("\n");
                default -> throw new IllegalStateException("Unexpected value: " + entry.getStatus());
            }
        }
        result.append("}");
        return result.toString();
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return value.toString(); // Убираем кавычки
        }
        if (value instanceof Map || value instanceof List) {
            return value.toString(); // Для сложных объектов используем toString()
        }
        return String.valueOf(value); // Универсальная обработка
    }
}
