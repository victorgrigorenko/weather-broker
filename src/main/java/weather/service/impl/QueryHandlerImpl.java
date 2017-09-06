package weather.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import weather.data.Location;
import weather.jms.IMessageSender;
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

    @Autowired
    IMessageSender sender;

    private String createJson(String city){
        String uri = uriRequest.createURI(city);
        return restTemplate.getForObject(uri, String.class);
    }

    @Override
    public boolean handle(String city) {
        Location location = parserWeather.parseJsonIntoEntity(createJson(city));
        if (location == null){
            return false;
        }
        sender.send(location);
        return true;
    }
}
