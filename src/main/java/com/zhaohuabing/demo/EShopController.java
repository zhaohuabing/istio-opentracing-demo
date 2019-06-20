package com.zhaohuabing.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EShopController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/checkout")
    public String checkout(@RequestHeader HttpHeaders headers) {
        String result = "";
        // Use HTTP GET in this demo. In a real world use case,We should use HTTP POST
        // instead.
        // The three services are bundled in one jar for simplicity. To make it work,
        // define three services in Kubernets.
        result += restTemplate.getForEntity("http://inventory:8080/createOrder", String.class).getBody();
        result += "<BR>";
        result += restTemplate.getForEntity("http://billing:8080/payment", String.class).getBody();
        result += "<BR>";
        result += restTemplate.getForEntity("http://delivery:8080/arrangeDelivery", String.class).getBody();
        return result;
    }
}
