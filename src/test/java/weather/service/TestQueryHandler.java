package weather.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import weather.data.Location;
import weather.jms.IMessageSender;
import weather.service.impl.QueryHandlerImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/test-root-context.xml"})
public class TestQueryHandler {

    @Mock
    private RestTemplate mockRestTemplate;

    @Mock
    private IUriGenerator mockUriRequest;

    @Spy
    @Autowired
    private IParserWeather<Location> testParserWeather;

    @Mock
    private IMessageSender mockSender;

    @InjectMocks
    QueryHandlerImpl testQueryHandlerImpl;

    @Autowired
    Location exampleLocation;

    private static String json, uri;

    @Test
    public void testHandleWithArgumentNull(){
        assertFalse(testQueryHandlerImpl.handle(null));
    }

    @Test
    public void testHandleWithCorrectArgument(){
        String city = "Saratov";
        when(mockUriRequest.createURI(city)).thenReturn(uri);
        when(mockRestTemplate.getForObject(uri, String.class)).thenReturn(json);

        testQueryHandlerImpl.handle(city);

        verify(testParserWeather).parseJsonIntoEntity(json);
        assertEquals(testParserWeather.parseJsonIntoEntity(json),exampleLocation);
        verify(mockSender).send(exampleLocation);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeClass
    public static void initJsonString() throws IOException {
        Path path = Paths.get("src/test/resources/testJSON.txt");
        json = new String(Files.readAllBytes(path));
        uri = new String("https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"Saratov\")&format=json&env=store://datatables.org/alltableswithkeys");
   }
}
