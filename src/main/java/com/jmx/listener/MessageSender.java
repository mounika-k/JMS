package com.jmx.listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.*;

    @Component

    public class MessageSender {

        @Autowired
        private ConnectionFactory connectionFactory;
        private JmsTemplate jmsTemplate;
        @Autowired
        private Queue queue;
        @PostConstruct
        public void init() {
            this.jmsTemplate = new JmsTemplate(connectionFactory);
        }

        public void sendMessage(String message) {
            System.out.println("sending: " + message);
            jmsTemplate.send(queue, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(message);
                }
            });
        }

}
