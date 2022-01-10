package com.jmx.listener;



import com.jmx.dao.ProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MessageReceiver {

    @Autowired
    private ConnectionFactory connectionFactory;
    private ConsumerService consumerService;
    private static final Boolean NON_TRANSACTED = false;
    private static final int MESSAGE_TIMEOUT_MILLISECONDS = 120;
    @Autowired
    private Queue queue;
    @PostConstruct
    public void init() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        this.consumerService = new ConsumerServiceImpl();
    }

    public List<ProductDetails> receiveMessage() {
        Connection connection;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(NON_TRANSACTED, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(queue);
            String text;
            // Synchronous message consumer
            int i = 1;
            List<ProductDetails> productDetailsList = new ArrayList<>();
            while (true) {
                Message message = consumer.receive(MESSAGE_TIMEOUT_MILLISECONDS);
                if (message != null) {
                    if (message instanceof TextMessage) {
                        ProductDetails productDetails;
                        text = ((TextMessage) message).getText();
                        System.out.println("Got " + (i++) + ". message: " + text);
                        productDetails = consumerService.onMessage(text);
                        productDetailsList.add(productDetails);
                        System.out.println("size" + productDetailsList.size());
                    }
                } else {
                    break;
                }
            }
            consumer.close();
            session.close();

            return productDetailsList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}