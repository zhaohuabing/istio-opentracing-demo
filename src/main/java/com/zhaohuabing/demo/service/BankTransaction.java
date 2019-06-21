package com.zhaohuabing.demo.service;

/**
 * Huabing Zhao
 */
public class BankTransaction {
    public void transfer() {
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}