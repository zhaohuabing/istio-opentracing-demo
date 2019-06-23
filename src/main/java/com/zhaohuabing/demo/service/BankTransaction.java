package com.zhaohuabing.demo.service;

import org.springframework.stereotype.Component;

/**
 * Huabing Zhao
 */
@Component
public class BankTransaction {
    public void transfer() {
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}