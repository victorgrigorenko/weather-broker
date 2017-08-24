package weather.data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FORECAST")
public class Forecast {

    private Integer id;
    private Integer code;
    private Date date;
    private String day;
    private Integer high;
    private Integer low;
    private String text;
    private String id_location;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "thisCode")
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "thisDate")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "thisDay")
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Column(name = "high")
    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    @Column(name = "low")
    public Integer getLow() {
        return low;
    }

    public void setLow(Integer low) {
        this.low = low;
    }

    @Column(name = "description")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "id_location")
    public String getId_location() {
        return id_location;
    }

    public void setId_location(String id_location) {
        this.id_location = id_location;
    }
}
