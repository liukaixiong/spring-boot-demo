package com.spring.boot.plugin.test.service;

import com.spring.api.ISendService;

/**
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/12/14 - 19:12
 */
public class WeiXinService implements ISendService {

    @Override
    public void send(String text) {
        System.out.println("微信发送:" + text);
    }
}
