package weather.data.maintenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import weather.data.Location;
import weather.service.AbstractableService;

@Component
public class QueryHandler {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired(required = true)
    @Qualifier("locationService")
    private AbstractableService<Location,String> locationService;


    public String createJson(String city){
        String uri = UriGenerator.createURI(city);
        return restTemplate.getForObject(uri, String.class);
    }

    public boolean saveDB(Location location){
        locationService.saveToDatabase(location);
        return true;
    }
}
