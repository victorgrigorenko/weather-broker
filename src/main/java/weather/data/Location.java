package weather.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "location")
public class Location implements Serializable {

    @Id
    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "region")
    private String region;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "id_location")
    private List<Forecast> forecasts;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }


    public void addForecast(Forecast forecast) {
        forecasts.add(forecast);
        forecast.setLocation(this.getCity());
    }

    public void removeForecast(Forecast forecast) {
        forecasts.remove(forecast);
        forecast.setLocation(null);
    }



    @Override
    public String toString(){

        return "Location{\n" +
                " city : " + city +
                ",\n country : " + country +
                ",\n region : " + region + ",\n"+
                getForecastsAsText()+
                "}";
    }

    private String getForecastsAsText(){
        StringBuilder forecasts = new StringBuilder(" forecast : [\n");
        if (this.forecasts != null) {
            for (Forecast f : this.forecasts) {
                forecasts.append(" {\n\tcode : " + f.getCode());
                forecasts.append(",\n\tdate : " + f.getDate());
                forecasts.append(",\n\tday : " + f.getDay());
                forecasts.append(",\n\thigh : " + f.getHigh());
                forecasts.append(",\n\tlow : " + f.getLow());
                forecasts.append(",\n\ttext : " + f.getText() + "\n }");
            }
        }
        forecasts.append("\n]");
        return String.valueOf(forecasts);
    }
}
