package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;

public class FileProcessor {

    public static Map<String, Object> parseFile(String filePath) throws Exception {
        // Создаём объект для работы с JSON
        ObjectMapper objectMapper = new ObjectMapper();

        // Читаем и парсим JSON из файла
        return objectMapper.readValue(new File(filePath), Map.class);
    }

    public static void main(String[] args) throws Exception {
        // Пути до файлов
        String filePath1 = "file1.json";
        String filePath2 = "file2.json";

        // Читаем и выводим содержимое первого файла
        Map<String, Object> data1 = parseFile(filePath1);
        System.out.println("Содержимое первого файла: " + data1);

        // Читаем и выводим содержимое второго файла
        Map<String, Object> data2 = parseFile(filePath2);
        System.out.println("Содержимое второго файла: " + data2);
    }
}
