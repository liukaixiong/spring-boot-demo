package com.lkx.demo.springcloud.provider.hystrix;

import com.lkx.demo.springcloud.provider.utils.ThreadLocalUtils;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class HystrixMethodInvoke {
    private Logger logger = LoggerFactory.getLogger(getClass());
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @HystrixCommand(commandProperties = {
        // 参考 {@link com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager}
        @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ENABLED, value = "true"),
        //        @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_FORCE_OPEN, value = "true"),
        @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "5557"),
        @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "50"),
        @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "5"),
        @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_INTERRUPT_ON_TIMEOUT, value = "false"),
        @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "5000"),
        @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "THREAD")}, fallbackMethod = "timeOutFallback", observableExecutionMode = ObservableExecutionMode.LAZY)
    public String timeOut(int timeOut) throws Exception {
        // HystrixCommandProperties.ExecutionIsolationStrategy.THREAD
        //        HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE
        Thread.sleep(timeOut);
        logger.info("执行了成功逻辑 : " + ThreadLocalUtils.get());
        return "OK";
    }

    @HystrixCommand(commandProperties = {
        // 参考 {@link com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager}
        @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ENABLED, value = "true"),
        //        @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_FORCE_OPEN, value = "true"),
        @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "5557"),
        @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "50"),
        @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "5"),
        @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_INTERRUPT_ON_TIMEOUT, value = "false"),
        @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "5000"),
        @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "THREAD")}, fallbackMethod = "timeOutFallback", observableExecutionMode = ObservableExecutionMode.LAZY)
    public Future<String> timeOutAsync(int timeOut) throws Exception {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(timeOut);
                return "OK";
            }
        };
        Future<String> submit = executorService.submit(callable);
        return submit;
    }

    @HystrixCommand(commandProperties = {
        // 参考 {@link com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager}
        @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ENABLED, value = "true"),
        //        @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_FORCE_OPEN, value = "true"),
        @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "5557"),
        @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "50"),
        @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "5"),
        @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_INTERRUPT_ON_TIMEOUT, value = "true"),
        @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "5000"),
        @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "THREAD")}, fallbackMethod = "timeOutFallback", observableExecutionMode = ObservableExecutionMode.LAZY)
    public String error(int timeOut) throws Exception {
        logger.info("正常方法调用 : error");
        int i = 1 / 0;
        return "OK";
    }

    public String timeOutFallback(int timeOut) throws Exception {
        String body = ThreadLocalUtils.get();
        logger.info("执行失败回调的方法 : " + body);
        return "fallback OK!";
    }

}
