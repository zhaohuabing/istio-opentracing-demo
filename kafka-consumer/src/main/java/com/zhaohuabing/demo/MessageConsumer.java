package com.zhaohuabing.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = "eshop-topic")
    public void receive(String payload) {
        System.out.println("*** receive kafka message ***");
    }
}