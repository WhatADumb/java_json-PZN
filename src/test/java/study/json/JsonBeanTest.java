package study.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonBeanTest {
    @Test
    void testWrite() throws IOException {
        Address address = new Address();
        address.setCity("Semarang");
        address.setCountry("Indonesia");;
        address.setStreet("Gawangan");

        Person person = new Person();
        person.setId(1);
        person.setName("Fadhil Firmansyah");
        person.setHobbies(List.of("Coding", "Gaming", "Studying"));
        person.setAddress(address);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(person);
        mapper.writeValue(new File("src/main/resources/bean.json"), person);

        System.out.println(json);
    }

    @Test
    void testRead() throws IOException {
        String template = """
                {"id":1,"name":"Fadhil Firmansyah","hobbies":["Coding","Gaming","Studying"],"address":{"country":"Indonesia","city":"Semarang","street":"Gawangan"}}
                """;

        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(template, Person.class);
        Person perona = mapper.readValue(new File("src/main/resources/bean.json"), Person.class);

        Assertions.assertEquals("Fadhil Firmansyah", person.getName());
        Assertions.assertEquals(1, perona.getId());
        Assertions.assertEquals("Indonesia", person.getAddress().getCountry());
    }
}
