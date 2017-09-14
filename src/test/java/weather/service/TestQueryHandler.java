package weather.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;
import weather.data.Location;
import weather.jms.IMessageSender;
import weather.service.impl.QueryHandlerImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:**/test-root-context.xml"})
public class TestQueryHandler {

    @Mock
    private RestTemplate mockRestTemplate;

    @Mock
    private IUriGenerator testUriRequest;

    @Mock
    private IParserWeather<Location> testParserWeather;

    @Mock
    private IMessageSender mockSender;

    @InjectMocks
    QueryHandlerImpl testQueryHandlerImpl;


    private static String json, uri;


    @Test
    public void testHandleWithArgumentNull(){
        assertFalse(testQueryHandlerImpl.handle(null));
    }

    @Test
    public void testHandleWithCorrectArgument() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = testQueryHandlerImpl.getClass().getDeclaredMethod("createJson", String.class);
        method.setAccessible(true);
        when(method.invoke(testQueryHandlerImpl,"Saratov")).thenReturn(json);
        when(testParserWeather.parseJsonIntoEntity(json)).thenReturn(new Location());
        doNothing().when(mockSender).send(any(Location.class));
        testQueryHandlerImpl.handle("Saratov");
        verify(mockSender, atLeastOnce()).send(any(Location.class));
    }


    @BeforeClass
    public static void initJsonString() throws IOException {
        Path path = Paths.get("src/test/resources/testJSON.txt");
        json = new String(Files.readAllBytes(path));
        uri = new String("https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"Saratov\")&format=json&env=store://datatables.org/alltableswithkeys");

    }
}
