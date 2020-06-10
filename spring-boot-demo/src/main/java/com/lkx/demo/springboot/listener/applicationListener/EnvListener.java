package com.lkx.demo.springboot.listener.applicationListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 环境变量监听
 *
 * @author ： liukx
 * @time ： 2019/5/31 - 17:26
 */
public class EnvListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
        logger.info(" 环境变量监听被触发 ... " + applicationEnvironmentPreparedEvent.getEnvironment().getPropertySources().toString());
    }
}
