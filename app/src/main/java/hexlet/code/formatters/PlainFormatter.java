package hexlet.code.formatters;

import hexlet.code.DiffEntry;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Форматтер для вывода в текстовом формате plain
public class PlainFormatter {
    public static String format(List<DiffEntry> diff) {
        return diff.stream()
                .map(entry -> switch (entry.getStatus()) {
                    case ADDED -> String.format("Свойство '%s' было добавлено со значением: %s",
                            entry.getKey(), formatValue(entry.getNewValue()));
                    case REMOVED -> String.format("Свойство '%s' было удалено", entry.getKey());
                    case CHANGED -> String.format("Свойство '%s' было обновлено. С %s на %s",
                            entry.getKey(), formatValue(entry.getOldValue()), formatValue(entry.getNewValue()));
                    default -> null; // Пропускаем неизмененные свойства
                })
                .filter(line -> line != null)
                .collect(Collectors.joining("\n"));
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        return value instanceof String ? "'" + value + "'" : value.toString();
    }
}
