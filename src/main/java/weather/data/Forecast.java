package weather.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "forecast")
public class Forecast implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "thisCode")
    private Integer code;

    @Temporal(TemporalType.DATE)
    @Column(name = "thisDate")
    private Date date;

    @Column(name = "thisDay")
    private String day;

    @Column(name = "high")
    private Integer high;

    @Column(name = "low")
    private Integer low;

    @Column(name = "description")
    private String text;

//    @ManyToOne
//    @JoinColumn(name = "id_location")//, foreignKey = @ForeignKey(name = "fk_id_location"))
//    private Location location;
    @Column(name = "id_location")//, foreignKey = @ForeignKey(name = "fk_id_location"))
    private String location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public Integer getLow() {
        return low;
    }

    public void setLow(Integer low) {
        this.low = low;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Forecast forecast = (Forecast) o;

        if (id != null ? !id.equals(forecast.id) : forecast.id != null) return false;
        if (code != null ? !code.equals(forecast.code) : forecast.code != null) return false;
        if (date != null ? !date.equals(forecast.date) : forecast.date != null) return false;
        if (day != null ? !day.equals(forecast.day) : forecast.day != null) return false;
        if (high != null ? !high.equals(forecast.high) : forecast.high != null) return false;
        if (low != null ? !low.equals(forecast.low) : forecast.low != null) return false;
        if (text != null ? !text.equals(forecast.text) : forecast.text != null) return false;
        return location != null ? location.equals(forecast.location) : forecast.location == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (high != null ? high.hashCode() : 0);
        result = 31 * result + (low != null ? low.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return "Forecast{" +
                "id=" + id +
                ", thisCode=" + code +
                ", thisDate=" + date +
                ", thisDay=" + day +
                ", high=" + high +
                ", low=" + low +
                ", description=" + text +
                ", city=" + getLocation()+//.getCity() +
                "}";
    }
}
