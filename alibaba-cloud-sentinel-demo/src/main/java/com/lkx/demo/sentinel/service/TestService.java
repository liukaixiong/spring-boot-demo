package com.lkx.demo.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @SentinelResource(value = "sayHello" , blockHandler = "blockHandler1", fallback = "allError")
    public String sayHello(String name) {
        return "Hello, " + name;
    }

    @SentinelResource(value = "sayHello1", blockHandler = "blockHandler1")
    public String sayHello1(String name) {
        return "Hello, " + name;
    }

    @SentinelResource(value = "sayHello2", fallback = "allError")
    public String sayHello2(String name) {
        return "Hello, " + name;
    }

    /**
     * 针对限流、降级、系统保护的时候触发
     *
     * @return
     */
    public String blockHandler1(String name) {
        logger.warn("降级方法: blockHandler1 , " + name);
        return "降级方法: blockHandler1 , " + name;
    }

    /**
     * 会针对所有类型的异常
     *
     * @param name
     * @return
     */
    public String allError(String name) {
        logger.warn("降级方法: allError , " + name);
        return "降级方法: allError , " + name;
    }

}