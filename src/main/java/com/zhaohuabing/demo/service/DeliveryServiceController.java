package com.zhaohuabing.demo.service;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryServiceController {

    @RequestMapping(value = "/arrangeDelivery")
    public String createOrder(@RequestHeader HttpHeaders headers) {
        return "Your order is on the way!";
    }
}
