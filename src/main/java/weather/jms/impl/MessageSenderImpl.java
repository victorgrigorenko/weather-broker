package weather.jms.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import weather.data.Location;
import weather.jms.IMessageSender;

import javax.enterprise.context.ApplicationScoped;
import javax.jms.Destination;

@Component
@ApplicationScoped
class MessageSenderImpl implements IMessageSender {

    @Autowired
    @Qualifier("destination")
    private Destination destination;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(Location location) {
        jmsTemplate.convertAndSend(destination, location);
    }

}