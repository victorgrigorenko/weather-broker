package weather.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import weather.data.Location;
import weather.service.AbstractableService;
import weather.service.IParserWeather;
import weather.service.IQueryHandler;
import weather.service.IUriGenerator;

@Service("handler")
public class QueryHandlerImpl implements IQueryHandler{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IUriGenerator uriRequest;

    @Autowired
    private IParserWeather<Location> parserWeather;

    @Autowired(required = true)
    @Qualifier("locationService")
    private AbstractableService<Location,String> locationService;


    private String createJson(String city){
        String uri = uriRequest.createURI(city);
        return restTemplate.getForObject(uri, String.class);
    }

    private boolean saveDB(Location location){
        locationService.saveToDatabase(location);
        return true;
    }

    @Override
    public boolean handle(String city) {
        Location location = parserWeather.parseJsonIntoEntity(createJson(city));
        if (location == null){
            return false;
        }
        return saveDB(location);
    }
}
