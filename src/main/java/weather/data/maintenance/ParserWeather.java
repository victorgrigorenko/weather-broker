package weather.data.maintenance;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import weather.data.Forecast;
import weather.data.Location;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ParserWeather {
    private static ParserWeather instance;
    private ObjectMapper mapper;

    private ParserWeather(){
        mapper = new ObjectMapper();
    }

    public static ParserWeather getInstance(){
        if (instance == null){
            instance = new ParserWeather();
        }
        return instance;
    }

    public Location parseJsonIntoLocationEntity(String jsonString){
        JsonNode root = null;
        try {
            root = mapper.readTree(jsonString);
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
            forecast.setLocation(location);
            list.add(forecast);
        }
        location.setForecasts(list);
        return location;
    }

}
