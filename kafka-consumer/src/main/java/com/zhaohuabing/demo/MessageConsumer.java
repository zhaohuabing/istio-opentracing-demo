package com.zhaohuabing.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.contrib.kafka.TracingKafkaUtils;

@Component
public class MessageConsumer {

    @Autowired
    private Tracer tracer;

    @Autowired
    private RestTemplate restTemplate;

    @KafkaListener(topics = "eshop-topic")
    public void receiveMessage(ConsumerRecord<String, String> record, Acknowledgment acks) {
        SpanContext spanContext = TracingKafkaUtils.extractSpanContext(record.headers(), tracer);
        restTemplate.getForEntity("http://notification:8080/sendEmail", String.class);
        System.out.println("*** receive kafka message ***");
        acks.acknowledge();
    }
}