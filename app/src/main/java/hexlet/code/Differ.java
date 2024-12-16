package hexlet.code;

import java.util.List;
import java.util.Map;

// Основной класс для генерации diff и выбора формата вывода
public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        Map<String, Object> map1 = Parser.parse(filePath1);
        Map<String, Object> map2 = Parser.parse(filePath2);
        List<DiffEntry> diff = DiffEntry.generateDiff(map1, map2);
        return Formatter.format(diff, formatName);
    }
}
