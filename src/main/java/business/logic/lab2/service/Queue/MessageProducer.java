package business.logic.lab2.service.Queue;

import business.logic.lab2.dto.EmailMessageDTO;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
public class MessageProducer {
    @Autowired
    private Queue queue;
    @Autowired
    private PooledConnectionFactory pooledConnectionFactory;

    public void produceMessage( EmailMessageDTO messageDTO) throws JMSException{

        final Connection producerConnection = pooledConnectionFactory.createConnection();
        producerConnection.start();

        final Session producerSession = producerConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        final javax.jms.MessageProducer producer = producerSession.createProducer(queue);

        ObjectMessage objectMessage = producerSession.createObjectMessage(messageDTO);

        producer.send(objectMessage);
        System.out.println("Message is sent to queue.");

        producer.close();
        producerSession.close();
        producerConnection.close();

    }
}
