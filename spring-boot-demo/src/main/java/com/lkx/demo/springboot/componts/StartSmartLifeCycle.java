package com.lkx.demo.springboot.componts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.LifecycleProcessor;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * 当SpringBean 被加载结束后如果isAutoStartup()返回true,则执行start() 方法
 *
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/9/3 - 10:38
 */
@Component
public class StartSmartLifeCycle implements SmartLifecycle, LifecycleProcessor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private volatile boolean isRunning;

    @Override
    public void start() {
        logger.info("容器执行完成之后会回调该start方法...");
        isRunning = true;
    }

    @Override
    public void stop() {
        isRunning = false;
        logger.info("容器关闭结束之后会回调该stop方法...");
    }

    @Override
    public boolean isRunning() {
        // 当容器执行完成之后请设置成true
        return isRunning;
    }

    @Override
    public void onRefresh() {
        logger.info("容器刷新完成之后会回调该onRefresh方法...");
    }

    @Override
    public void onClose() {
        logger.info("容器关闭之后会回调该onClose方法...");
    }
}
