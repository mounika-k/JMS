package com.jmx;

import com.jmx.config.Config;
import com.jmx.listener.MessageSender;
import org.apache.activemq.ActiveMQQueueSender;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import javax.jms.JMSException;
import javax.jms.Queue;


@SpringBootTest
 class PriceControllerTest {
    private static final Log log = LogFactory.getLog(PriceControllerTest.class);
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private Queue queue;

    MessageSender ms;

    @Before
     void setup() throws JMSException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
         ms = context.getBean(MessageSender.class);
    }
    @Test
     void productDetailsTest() throws Exception {
       ms.sendMessage("hi");
    }

    @Test
     void productDetailsTest1() throws Exception {
        ms.sendMessage(null);
    }

}


