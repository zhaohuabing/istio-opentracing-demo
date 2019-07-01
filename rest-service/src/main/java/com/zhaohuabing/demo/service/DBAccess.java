package com.zhaohuabing.demo.service;

import com.zhaohuabing.demo.instrument.Traced;

import org.springframework.stereotype.Component;

/**
 * Huabing Zhao
 */
@Component
public class DBAccess {

    @Traced
    public void save2db() {
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}