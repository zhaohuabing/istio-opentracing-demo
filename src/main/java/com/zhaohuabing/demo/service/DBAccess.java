package com.zhaohuabing.demo.service;

/**
 * Huabing Zhao
 */
public class DBAccess {
    public void save2db() {
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}