package study.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class JsonObjectTest {
    @Test
    void testWriteJson() throws IOException {
        Path path = Path.of("src/main/resources/sample.json");
        OutputStream outputStream = Files.newOutputStream(path);

        Map<String, Object> objectMap = Map.of(
                "name", Map.of("first", "Fadhil", "last", "firmansyah"),
                "age", 19,
                "isAlive", true
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(objectMap);
        objectMapper.writeValue(outputStream, objectMap);

        System.out.println(json);
    }

    @Test
    void testReadJson() throws IOException {
        String json = """
                {"age":19,"name":{"first":"Fadhil","last":"firmansyah"},"isAlive":true}
                """;

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> objectMap = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
        Map<String, Object> jsonFile = mapper.readValue(new File("src/main/resources/sample.json"), new TypeReference<Map<String, Object>>() {});

        Assertions.assertEquals(19, objectMap.get("age"));
        Assertions.assertEquals(true, jsonFile.get("isAlive"));
    }
}
