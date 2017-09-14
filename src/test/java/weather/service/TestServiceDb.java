package weather.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import weather.data.Location;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class TestServiceDb extends TestAbstractService{

    @Autowired
    Location exampleLocation;

    @Autowired
    private AbstractableService<Location,String> testLocationService;

    @Test
    public void testSaveToDBEntity() {
        testLocationService.saveToDatabase(exampleLocation);
        Location location = testLocationService.get(exampleLocation.getCity());
        assertEquals(exampleLocation, location);
    }

    @Test
    public void testGetAllEntities() {
        List<Location> locations = testLocationService.getAll();
        assertEquals(0, locations.size());
        Location location;
        for(int i=0;i<5;i++){
            location = new Location();
            location.setCity(String.valueOf(i));
            testLocationService.saveToDatabase(location);
        }
        locations = testLocationService.getAll();
        assertEquals(5, locations.size());
    }


}
