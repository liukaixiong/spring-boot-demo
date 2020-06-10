package com.lkx.demo.springboot.listener.applicationListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 启动过程中出现异常
 *
 * @author ： liukx
 * @time ： 2019/5/31 - 17:33
 */
public class FailedEventListener implements ApplicationListener<ApplicationFailedEvent> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ApplicationFailedEvent applicationFailedEvent) {
        logger.info("启动过程中出现异常 会被回调 .. " + applicationFailedEvent.getException().getLocalizedMessage());
    }
}
