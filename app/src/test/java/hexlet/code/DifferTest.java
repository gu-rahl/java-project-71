package hexlet.code;

import hexlet.code.formatters.StylishFormatter;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static hexlet.code.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    @Test
    void testNestedStructures() throws Exception {
        Map<String, Object> map1 = parse("src/test/resources/nested1.json");
        Map<String, Object> map2 = parse("src/test/resources/nested2.json");

        assert map1 != null : "map1 is null";
        assert map2 != null : "map2 is null";

        var diff = Differ.generate(map1, map2);

        String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }
                """;

        assertEquals(expected.trim(), StylishFormatter.format(diff).trim());
    }
}
