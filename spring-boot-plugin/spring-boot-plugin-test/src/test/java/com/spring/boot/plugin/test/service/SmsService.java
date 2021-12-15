package com.spring.boot.plugin.test.service;

import com.spring.api.ISendService;

/**
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/12/14 - 19:09
 */
public class SmsService implements ISendService {

    @Override
    public void send(String text) {
        System.out.println("短信发送:" + text);
    }
}
