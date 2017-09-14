package weather.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/test-root-context.xml"})
public class TestUriGenerator {

    @Autowired
    @Qualifier("testUriRequest")
    public IUriGenerator testUriRequest;

    @Test
    public void testCorrectCreateURIWithArgumentSaratov(){
        String expected = "https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"Saratov\")&format=json&env=store://datatables.org/alltableswithkeys";
        String actual = testUriRequest.createURI("Saratov");
        assertEquals(expected,actual);
    }

    @Test
    public void testCorrectCreateURIWithArgumentNull(){
        String expected = "https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"null\")&format=json&env=store://datatables.org/alltableswithkeys";
        String actual = testUriRequest.createURI(null);
        assertEquals(expected,actual);
    }
}
