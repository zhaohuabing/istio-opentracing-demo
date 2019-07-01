package com.zhaohuabing.demo.service;

import org.springframework.kafka.annotation.KafkaListener;

public class EmailSender {

    @KafkaListener(topics = "eshop-topic")
    public void receive(String payload) {
        System.out.println("Send out confirmation email");
    }
}