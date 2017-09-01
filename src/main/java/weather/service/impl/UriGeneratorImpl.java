package weather.service.impl;

import org.springframework.stereotype.Service;
import weather.service.IUriGenerator;

@Service("uriRequest")
class UriGeneratorImpl implements IUriGenerator {

    public String createURI(String city){
        String baseURI = "https://query.yahooapis.com/v1/public/yql";
        String yqlQuery = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\""+city+"\")";
        String uri = baseURI+"?q=" + yqlQuery + "&format=json&env=store://datatables.org/alltableswithkeys";

        return uri;
    }
}
