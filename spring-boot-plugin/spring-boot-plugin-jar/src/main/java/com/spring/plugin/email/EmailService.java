package com.spring.plugin.email;

import com.spring.api.ISendService;
import org.springframework.stereotype.Component;

/**
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/12/14 - 19:29
 */
@Component
public class EmailService implements ISendService {

    @Override
    public void send(String text) {
        System.out.println("邮箱:" + text);
    }
}
