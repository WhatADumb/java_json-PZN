package study.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class FeatureTest {
    @Test
    void testMapFeature() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        String json = """
                {"ID":1, "name": "alpha"}
                """;

        Person person = mapper.readValue(json, Person.class);

        Assertions.assertEquals(1, person.getId());
        Assertions.assertEquals("alpha", person.getName());
    }

    @Test
    void testDeserialization() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        String json = """
                {"ID":1, "name": "alpha", "age": 90, "hobbies": "Coding"}
                """;

        Person person = mapper.readValue(json, Person.class);

        Assertions.assertEquals(1, person.getId());
        Assertions.assertEquals("alpha", person.getName());
    }

    @Test
    void testSerialization() throws JsonProcessingException {
        Address address = new Address();
        address.setCity("Semarang");
        address.setCountry("Indonesia");;
        address.setStreet("Gawangan");

        Person person = new Person();
        person.setId(1);
        person.setName("Fadhil Firmansyah");
        person.setHobbies(List.of("Coding", "Gaming", "Studying"));
        person.setAddress(address);

        ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);
        String json = mapper.writeValueAsString(person);

        System.out.println(json);
    }

    @Test
    void testSerializationInclusion() throws JsonProcessingException {Person person = new Person();
        person.setId(1);
        person.setName("Fadhil Firmansyah");
        person.setHobbies(List.of("Coding", "Gaming", "Studying"));

        ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = mapper.writeValueAsString(person);

        System.out.println(json);
    }
}
