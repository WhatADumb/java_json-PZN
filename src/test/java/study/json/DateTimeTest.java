package study.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeTest {
    @Test
    void testDateMilis() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper()
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);

        Person person = new Person();
        person.setId(1);
        person.setName("Fadhil");
        person.setCreatedAt(new Date());
        person.setUpdatedAt(new Date());

        String json = mapper.writeValueAsString(person);
        System.out.println(json);
    }

    @Test
    void testDateFormat() throws JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss / dd-MM-yyyy");

        ObjectMapper mapper = new ObjectMapper()
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .setDateFormat(format);

        Person person = new Person();
        person.setId(1);
        person.setName("Fadhil");
        person.setCreatedAt(new Date());
        person.setUpdatedAt(new Date());

        String json = mapper.writeValueAsString(person);
        System.out.println(json);
    }
}
