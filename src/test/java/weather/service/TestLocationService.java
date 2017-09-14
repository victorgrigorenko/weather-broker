package weather.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import weather.dao.AbstarctableDao;
import weather.data.Location;
import weather.service.impl.LocationServiceImpl;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:**/test-root-context.xml"})
public class TestLocationService {

    @Mock
    private AbstarctableDao<Location,String> testLocationDao;

    @InjectMocks
    private LocationServiceImpl testLocationService;


    @Test
    public void testGetEntity(){
        testLocationService.get(anyString());
        verify(testLocationDao).get(anyString());
    }

    @Test
    public void testGetAllEntities(){
        assertEquals(testLocationService.getAll(),new ArrayList<>());
        verify(testLocationDao).getAll();
    }

    @Test
    public void testSaveToDataBaseEntity(){
        testLocationService.saveToDatabase(new Location());
        verify(testLocationDao).saveToDatabase(any(Location.class));
    }

}