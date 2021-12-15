package com.spring.plugin.dingding;

import com.spring.api.ISendService;
import com.spring.api.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/12/14 - 19:11
 */
@Component
public class DingDingService implements ISendService {

    @Autowired
    private ILogService logService;

    @Override
    public void send(String text) {
        logService.println("钉钉发送:" + text);
    }
}
