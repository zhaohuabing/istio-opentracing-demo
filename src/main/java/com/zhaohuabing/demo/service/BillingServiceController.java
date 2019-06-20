package com.zhaohuabing.demo.service;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingServiceController {

    @RequestMapping(value = "/payment")
    public String payment(@RequestHeader HttpHeaders headers) {
        return "Your order has been paid!";
    }
}
