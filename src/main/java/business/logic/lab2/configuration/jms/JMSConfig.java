package business.logic.lab2.configuration.jms;

import business.logic.lab2.service.Queue.MessageConsumer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import javax.jms.Queue;
import java.util.Arrays;

@Configuration
public class JMSConfig {

    @Autowired
    MessageConsumer consumer;

    @Bean
    public ActiveMQConnectionFactory receiverActiveMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory =
                new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setTrustedPackages(Arrays.asList("business.logic.lab2.dto", "java.util", "java.sql"));
        return activeMQConnectionFactory;
    }


    @Bean
    public Queue queue() {
        return new ActiveMQQueue("email-queue");
    }

    @Bean
    public MessageListenerContainer messageListenerContainer(ActiveMQConnectionFactory receiverActiveMQConnectionFactory) {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(receiverActiveMQConnectionFactory);
        container.setDestinationName("email-queue");
        container.setMessageListener(consumer);
        return container;
    }

    @Bean
    public PooledConnectionFactory pooledConnectionFactory(ActiveMQConnectionFactory receiverActiveMQConnectionFactory) {
        final PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(receiverActiveMQConnectionFactory);
        return pooledConnectionFactory;
    }
}
