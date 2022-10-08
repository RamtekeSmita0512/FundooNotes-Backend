package fundoonotesapplication.rabbitmq;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fundoonotesapplication.utility.EmailService;

import java.util.Date;

@Component
public class MessageProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    EmailService emailService;

    public void sendMessage(String token) {

        emailService.sendEmail(token);
        System.out.println(new Date());
        rabbitTemplate.convertAndSend(RabbitMQConfig.ROUTING_KEY, token);
        System.out.println("Is listener returned ::: "+rabbitTemplate.isReturnListener());

    }

//    @RabbitListener(queues = "message_queue")
//    @RabbitHandler
//    public void recievedMessage(MailObject user) {
//        sendEmail(user.getEmail());
//        System.out.println("Recieved Message from rabbit-MQ : "+user);
//    }

}
