package weather.service;


public interface IParserWeather<T> {
    T parseJsonIntoEntity(String json);
}
