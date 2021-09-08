package com.lkx.demo.springcloud.provider.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixInvokable;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * hystrix 各个时机命令的回调触发
 */
public class MyHystrixHooK extends HystrixCommandExecutionHook {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public <T> Exception onError(HystrixInvokable<T> commandInstance, HystrixRuntimeException.FailureType failureType,
        Exception e) {
        logger.warn("onError : " + e.getMessage());
        return super.onError(commandInstance, failureType, e);
    }

    @Override
    public <T> Exception onExecutionError(HystrixInvokable<T> commandInstance, Exception e) {
        System.out.println(e.getMessage());
        logger.warn("onExecutionError : " + e.getMessage());
        return super.onExecutionError(commandInstance, e);
    }

    @Override
    public <T> Exception onFallbackError(HystrixInvokable<T> commandInstance, Exception e) {
        logger.warn("onFallbackError : " + e.getMessage());
        return super.onFallbackError(commandInstance, e);
    }

    @Override
    public <T> void onFallbackStart(HystrixInvokable<T> commandInstance) {
        logger.warn("开始执行onFallbackStart .... ");

        if (commandInstance instanceof HystrixCommand) {
            HystrixCommand hystrixCommand = (HystrixCommand)commandInstance;
            Throwable executionException = hystrixCommand.getExecutionException();
            StringBuilder msg = new StringBuilder();
            if (executionException instanceof HystrixTimeoutException) {
                Integer timeOut = hystrixCommand.getProperties().executionTimeoutInMilliseconds().get();
                msg.append("配置的超时时间: " + timeOut);
            }

            logger.warn("触发异常: " + executionException.toString() + "\t " + msg.toString());
        }
        super.onFallbackStart(commandInstance);
    }

    @Override
    public <T> T onFallbackEmit(HystrixInvokable<T> commandInstance, T value) {
        logger.warn("开始执行onFallbackEmit .... ");
        return super.onFallbackEmit(commandInstance, value);
    }
}
