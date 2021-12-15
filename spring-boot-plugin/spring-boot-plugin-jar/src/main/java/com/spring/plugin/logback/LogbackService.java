package com.spring.plugin.logback;

import com.spring.api.ILogService;
import org.springframework.stereotype.Component;

/**
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/12/15 - 13:40
 */
@Component
public class LogbackService implements ILogService {

    @Override
    public void println(String text) {
        System.out.println(getClass().getSimpleName() + " : " + text);
    }
}
