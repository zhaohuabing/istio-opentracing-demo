package com.zhaohuabing.demo.service;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Huabing Zhao
 */
@RestController
public class DeliveryServiceController {

    @RequestMapping(value = "/arrangeDelivery")
    public String arrangeDelivery(@RequestHeader HttpHeaders headers) {
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Your order is on the way!";
    }
}
