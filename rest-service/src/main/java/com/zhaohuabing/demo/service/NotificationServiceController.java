package com.zhaohuabing.demo.service;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Huabing Zhao
 */
@RestController
public class NotificationServiceController {

    @RequestMapping(value = "/sendEmail")
    public String sendEmail(@RequestHeader HttpHeaders headers) throws InterruptedException {
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Email sent out!");
        return "Email sent out!";
    }
}
