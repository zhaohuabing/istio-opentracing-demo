package com.zhaohuabing.demo;

import java.util.Collections;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Huabing Zhao
 */
@Component
public class MessageConsumer {

    @Autowired
    private RestTemplate restTemplate;

    @KafkaListener(topics = "eshop-topic")
    public void receiveMessage(ConsumerRecord<String, String> record) {
        restTemplate
                .setInterceptors(Collections.singletonList(new TracingKafka2RestTemplateInterceptor(record.headers())));
        restTemplate.getForEntity("http://notification:8080/sendEmail", String.class);
        System.out.println("*** receive kafka message ***");
    }
}
