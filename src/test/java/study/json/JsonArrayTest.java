package study.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JsonArrayTest {
    @Test
    void testArrayJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<String> hobbies = List.of("Gaming", "Studying", "Reading");
        String jsonArray = mapper.writeValueAsString(hobbies);

        System.out.println(jsonArray);
    }

    @Test
    void testJsonArray() throws JsonProcessingException {
        String template = """
                ["Gaming","Studying","Reading"]
                """;

        ObjectMapper mapper = new ObjectMapper();
        List<String> json = mapper.readValue(template, new TypeReference<List<String>>() {});

        Assertions.assertEquals(List.of("Gaming", "Studying", "Reading"), json);
    }
}
