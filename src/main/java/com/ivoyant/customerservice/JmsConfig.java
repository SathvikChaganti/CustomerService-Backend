package com.ivoyant.customerservice;


import com.ivoyant.customerservice.model.Customer;
import com.ivoyant.customerservice.model.LogEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;


@Configuration
@EnableScheduling
public class JmsConfig {

    @Value ("Customer")
    private String queueName;

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostConstruct
    private void customizeJmsTemplate() {
        // Update the jmsTemplate's connection factory to cache the connection
        CachingConnectionFactory ccf = new CachingConnectionFactory();
        ccf.setTargetConnectionFactory(jmsTemplate.getConnectionFactory());
        jmsTemplate.setConnectionFactory(ccf);

        // By default Spring Integration uses Queues, but if you set this to true you
        // will send to a PubSub+ topic destination
        jmsTemplate.setPubSubDomain(false);
    }
    public Customer sendEvent(String className, String methodName, String message){
        LogEvent logEvent = buildEvent (className, methodName, message);
        jmsTemplate.convertAndSend(queueName, message);
        return null;
    }
    private LogEvent buildEvent(String className,String methodName, String message) {
        return new LogEvent (className, methodName, message, LocalDateTime.now ());
    }

}
