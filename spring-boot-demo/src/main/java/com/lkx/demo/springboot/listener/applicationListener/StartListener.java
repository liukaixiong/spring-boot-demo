package com.lkx.demo.springboot.listener.applicationListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * demo监听规则
 *
 * @author ： liukx
 * @time ： 2019/5/31 - 17:22
 */
public class StartListener implements ApplicationListener<ApplicationStartedEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        logger.info("程序运行时，监听开始");
    }
}
