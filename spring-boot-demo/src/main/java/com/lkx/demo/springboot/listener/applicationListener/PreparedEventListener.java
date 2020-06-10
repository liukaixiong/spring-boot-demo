package com.lkx.demo.springboot.listener.applicationListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 在refresh开始前，但在bean定义已被加载后
 *
 * @author ： liukx
 * @time ： 2019/5/31 - 17:30
 */
public class PreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent applicationPreparedEvent) {
        logger.info(" 在refresh开始前，但在bean定义已被加载后 ");
    }
}
