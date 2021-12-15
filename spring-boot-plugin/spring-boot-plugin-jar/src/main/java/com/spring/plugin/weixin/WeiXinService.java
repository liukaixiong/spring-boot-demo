package com.spring.plugin.weixin;

import com.spring.api.ISendService;
import org.springframework.stereotype.Component;

/**
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/12/14 - 19:12
 */
@Component
public class WeiXinService implements ISendService {

    @Override
    public void send(String text) {
        System.out.println("微信发送:" + text);
    }
}
