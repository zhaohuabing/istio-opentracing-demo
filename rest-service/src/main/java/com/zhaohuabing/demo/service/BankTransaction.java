package com.zhaohuabing.demo.service;

import com.zhaohuabing.demo.instrument.Traced;

import org.springframework.stereotype.Component;

/**
 * Huabing Zhao
 */
@Component
public class BankTransaction {
    @Traced
    public void transfer() {
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
