package weather.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "location")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    @Id
    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "region")
    private String region;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
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

    @Override
    public String toString(){
        return "Location{" +
                "city=" + city +
                ", country=" + country +
                ", region=" + region +
                "}";
    }
}
