package weather.jms;

import weather.data.Location;

public interface IMessageSender {
    void send(Location location);
}
