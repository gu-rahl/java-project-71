package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;

public class Formatter {

    public static String format(List<DiffEntry> diff, String formatName) throws Exception {
        switch (formatName) {
            case "plain":
                return PlainFormatter.format(diff);
            case "stylish":
                return StylishFormatter.format(diff);
            default:
                throw new Exception("Unsupported format: " + formatName);
        }
    }
}
