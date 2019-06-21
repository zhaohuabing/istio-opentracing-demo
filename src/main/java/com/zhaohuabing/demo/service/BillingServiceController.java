package com.zhaohuabing.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Huabing Zhao
 */
@RestController
public class BillingServiceController {

    @Autowired
    private BankTransaction bankTransaction;

    @Autowired
    private DBAccess dbAccess;

    @RequestMapping(value = "/payment")
    public String payment(@RequestHeader HttpHeaders headers) throws InterruptedException {
        bankTransaction.transfer();
        dbAccess.save2db();
        return "Your order has been paid!";
    }
}
