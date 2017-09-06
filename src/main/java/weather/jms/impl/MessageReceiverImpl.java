package weather.jms.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;
import weather.data.Location;
import weather.service.AbstractableService;

import javax.jms.*;


@Service
class MessageReceiverImpl implements MessageListener {

    @Autowired
    private MessageConverter messageConverter;

    @Autowired(required = true)
    @Qualifier("locationService")
    private AbstractableService<Location,String> locationService;

    @Override
    public void onMessage(Message message) {
        try {
            Location location = (Location) messageConverter.fromMessage(message);
            locationService.saveToDatabase(location);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
