package com.lkx.demo.springboot.listener.applicationListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * 在refresh之后，相关的回调处理完
 *
 * @author ： liukx
 * @time ： 2019/5/31 - 17:31
 */
public class ReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        logger.info(" 在refresh之后，相关的回调处理完 " + applicationReadyEvent.getSource());
    }
}
