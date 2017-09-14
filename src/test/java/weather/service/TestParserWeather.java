package weather.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import weather.data.Location;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/test-root-context.xml"})
public class TestParserWeather {
    @Autowired
    public IParserWeather<Location> testParserWeather;

    private static String jsonString, jsonNull;

    @Autowired
    private Location exampleLocation;


    @Test
    public void testParseJsonIntoEntityWithCorrectJson() throws ParseException {
        Location expected = exampleLocation;
        Location actual = testParserWeather.parseJsonIntoEntity(jsonString);
        assertEquals(expected,actual);
    }

    @Test
    public void testParseJsonIntoEntityWithArgumentNull() throws ParseException {
        assertNull(testParserWeather.parseJsonIntoEntity(null));
    }

    @Test
    public void testParseJsonIntoEntityWithArgumentEmpty() throws ParseException {
        assertNull(testParserWeather.parseJsonIntoEntity(""));
    }

    @Test
    public void testParseJsonIntoEntityWithNullJson() throws ParseException {
        assertNull(testParserWeather.parseJsonIntoEntity(jsonNull));
    }

    @BeforeClass
    public static void setUp() throws IOException {
        Path path = Paths.get("src/test/resources/testJSON.txt");
        jsonString = new String(Files.readAllBytes(path));
        jsonNull = "{\n" +
                " \"query\": {\n" +
                "  \"count\": 0,\n" +
                "  \"created\": \"2017-09-11T14:44:28Z\",\n" +
                "  \"lang\": \"ru-RU\",\n" +
                "  \"results\": null\n" +
                " }\n" +
                "}";
    }
}
