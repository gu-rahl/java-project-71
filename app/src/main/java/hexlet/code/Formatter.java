package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;

// Управление выбором форматтера в зависимости от типа вывода
public class Formatter {
    public static String format(List<DiffEntry> diff, String formatName) throws Exception {
        return switch (formatName) {
            case "json" -> JsonFormatter.format(diff);
            case "plain" -> PlainFormatter.format(diff);
            case "stylish" -> StylishFormatter.format(diff);
            default -> throw new Exception("Неподдерживаемый формат: " + formatName);
        };
    }
}
