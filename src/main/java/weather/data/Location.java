package weather.data;

import javax.persistence.*;

@Entity
@Table(name = "location")
public class Location {


    @Id // если такой город уже есть, то падает, при добавлении
    //@GeneratedValue(strategy=GenerationType.TABLE)
    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "region")
    private String region;


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

}
