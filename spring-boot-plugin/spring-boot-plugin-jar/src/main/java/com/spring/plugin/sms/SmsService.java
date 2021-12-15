package com.spring.plugin.sms;

import com.spring.api.ISendService;
import org.springframework.stereotype.Component;

/**
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/12/14 - 19:09
 */
@Component
public class SmsService implements ISendService {

    @Override
    public void send(String text) {
        System.out.println("短信发送:" + text);
    }
}
