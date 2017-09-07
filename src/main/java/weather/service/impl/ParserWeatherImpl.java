package weather.service.impl;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import weather.data.Forecast;
import weather.data.Location;
import weather.service.IParserWeather;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service("parserWeather")
class ParserWeatherImpl implements IParserWeather<Location>{
    private ObjectMapper mapper;

    public ParserWeatherImpl(){
        mapper = new ObjectMapper();
    }


    public Location parseJsonIntoEntity(String json){
        JsonNode root = null;
        try {
            root = mapper.readTree(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonNode nodeQuery = root.path("query");
        JsonNode nodeResults = nodeQuery.path("results"); // если города нет, то вернет null.
        if (nodeResults.asText().equals("null")) {
            return null;
        }
        JsonNode nodeChannel = nodeResults.path("channel");
        JsonNode nodeLocation = nodeChannel.path("location");

        Location location = new Location();
        if (!nodeLocation.isMissingNode()){
            location.setCity(nodeLocation.path("city").asText());
            location.setCountry(nodeLocation.path("country").asText());
            location.setRegion(nodeLocation.path("region").asText());
        }

        JsonNode nodeItem = nodeChannel.path("item");
        JsonNode nodeForecast = nodeItem.path("forecast");

        List<Forecast> list = new ArrayList<>();
        for (JsonNode node: nodeForecast) {
            Forecast forecast = new Forecast();
            forecast.setCode(node.path("code").asInt());
            try {
                String dateStr = node.path("date").asText();
                DateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
                forecast.setDate(format.parse(dateStr));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            forecast.setDay(node.path("day").asText());
            forecast.setHigh(node.path("high").asInt());
            forecast.setLow(node.path("low").asInt());
            forecast.setText(node.path("text").asText());
            forecast.setLocation(location.getCity());
            list.add(forecast);
        }
        location.setForecasts(list);
        return location;
    }

}
