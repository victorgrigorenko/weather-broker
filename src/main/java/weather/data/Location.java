package weather.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (city != null ? !city.equals(location.city) : location.city != null) return false;
        if (country != null ? !country.equals(location.country) : location.country != null) return false;
        if (region != null ? !region.equals(location.region) : location.region != null) return false;
        if (region != null ? !region.equals(location.region) : location.region != null) return false;
        if (forecasts == location.getForecasts())return true;
        if ((forecasts == null && location.getForecasts() != null)
                || (forecasts != null && location.getForecasts() == null)) return false;
        if (forecasts != null && location.getForecasts() != null ? forecasts.size() != location.getForecasts().size() : true) return false;
        if (forecasts.size() !=  location.getForecasts().size()) return false;
        Iterator<Forecast> thisIt = forecasts.iterator();
        Iterator<Forecast> argIt = location.getForecasts().iterator();
        while(thisIt.hasNext() && argIt.hasNext()){
            Forecast thisItem = thisIt.next();
            Forecast argItem = argIt.next();
            if (thisItem != null ? !thisItem.equals(argItem): argItem != null) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (forecasts != null ? forecasts.hashCode() : 0);
        return result;
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
