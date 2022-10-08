package fundoonotesapplication.rabbitmq;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fundoonotesapplication.utility.MailObject;

@Component
public class RabbitMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("EXCHANGE")
    private String exchange;
    @Value("ROUTING_KEY")
    private String routingKey;

    public void send(MailObject message) {
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
    }

}

