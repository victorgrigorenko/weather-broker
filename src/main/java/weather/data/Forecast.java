package weather.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "FORECAST")
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

    @ManyToOne
    @JoinColumn(name = "id_location", foreignKey = @ForeignKey(name = "fk_id_location"))
    private Location location;

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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
                ", id_location=" + getLocation().getCity() +
                "}";
    }

}
