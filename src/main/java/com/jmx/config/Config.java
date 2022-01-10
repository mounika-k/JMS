package com.jmx.config;


import com.jmx.listener.MessageReceiver;
import com.jmx.listener.MessageSender;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@EnableJms
@Configuration
@ComponentScan
public class Config {

   @Bean
    public Queue queue() {
        return new ActiveMQQueue("inmemory.queue");
    }


    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("vm://localhost");
        return connectionFactory;
    }

    @Bean
    public MessageSender messageSender(){
        MessageSender messageSender = new MessageSender();
        return messageSender;
    }

    @Bean
    public MessageReceiver messageReceiver(){
        MessageReceiver messageReceiver = new MessageReceiver();
        return  messageReceiver;
    }


}
