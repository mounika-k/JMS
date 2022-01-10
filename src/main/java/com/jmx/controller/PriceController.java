
package com.jmx.controller;

import com.jmx.config.Config;
import com.jmx.dao.ProductDetails;
import com.jmx.listener.MessageReceiver;
import com.jmx.listener.MessageSender;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;
import java.io.File;
import java.io.FileReader;
import java.util.List;

@RestController
public class PriceController {
   @Autowired
   MessageSender messageSender;
    @Autowired
    MessageReceiver messageReceiver;
    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private Queue queue;

    private static final Boolean NON_TRANSACTED = false;
    private static final long MESSAGE_TIME_TO_LIVE_MILLISECONDS = 0;


    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    public PriceController(MessageSender messageSender,
                           MessageReceiver messageReceiver) {
        this.messageSender = messageSender;
        this.messageReceiver = messageReceiver;
    }

    @GetMapping("/priceList")
    private List<ProductDetails> productDetails(){
        MessageReceiver mr = context.getBean(MessageReceiver.class);
        List<ProductDetails> data = mr.receiveMessage();
        return data;
    }

    @GetMapping("/priceListPost")
    private void productDetails(String message){
        MessageSender ms = context.getBean(MessageSender.class);
        Connection connection;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(NON_TRANSACTED, Session.CLIENT_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);
            producer.setTimeToLive(MESSAGE_TIME_TO_LIVE_MILLISECONDS);
            File file = ResourceUtils.getFile("classpath:data.csv");
            List<ProductDetails> productDetails = new CsvToBeanBuilder(new FileReader(file))
                   .withType(ProductDetails.class)
                   .build()
                    .parse();
            productDetails.stream().forEach(data ->{
                ms.sendMessage(data.toString());
            });
            // Cleanup
            producer.close();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

