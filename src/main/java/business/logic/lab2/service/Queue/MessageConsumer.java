package business.logic.lab2.service.Queue;

import business.logic.lab2.dto.EmailMessageDTO;
import business.logic.lab2.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@Service
public class MessageConsumer implements MessageListener {
    @Autowired
    EmailService emailSender;


    @Override
    public void onMessage(Message message) {
        System.out.println("Consumer started to process new message");
        try {
            EmailMessageDTO emailMessageDTO = (EmailMessageDTO) ((ObjectMessage) message).getObject();
            emailSender.sendEmail(emailMessageDTO);
            Thread.sleep(10 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Consumer finished to process new message");
    }
}
