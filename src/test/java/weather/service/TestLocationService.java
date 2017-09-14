package weather.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

import weather.dao.AbstarctableDao;
import weather.data.Location;
import weather.service.impl.LocationServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TestLocationService extends TestAbstractService{

    @Mock
    private AbstarctableDao<Location,String> testLocationDao;

    @InjectMocks
    private LocationServiceImpl testLocationService;

    @Spy
    @Autowired
    Location exampleLocation;

    @Spy
    List<Location> locations;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        locations = new ArrayList<>();
        locations.add(new Location());
        locations.add(exampleLocation);
    }

    @Test
    public void testGetEntity(){
        when(testLocationDao.get(anyString())).thenReturn(exampleLocation);
        assertEquals(testLocationService.get(exampleLocation.getCity()),exampleLocation);
    }

    @Test
    public void testGetAllEntities(){
        when(testLocationDao.getAll()).thenReturn(locations);
        assertEquals(testLocationService.getAll(),locations);
    }

    @Test
    public void testSaveToDataBaseEntity(){
        doNothing().when(testLocationDao).saveToDatabase(any(Location.class));
        testLocationService.saveToDatabase(new Location());
        verify(testLocationDao, atLeastOnce()).saveToDatabase(any(Location.class));
    }

}