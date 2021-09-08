package com.lkx.demo.sentinel.ext.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

import java.util.List;

public interface URLRestFallback<T, R> {

    /**
     * 关注的URL
     *
     * @return
     */
    List<String> url();

    /**
     * 失败回调的方法
     *
     * @param request   请求参数
     * @param body      请求体
     * @param execution 执行器
     * @param ex        异常描述
     * @return
     */
    R fallback(HttpRequest request, T body, ClientHttpRequestExecution execution, BlockException ex);

    /**
     * 熔断触发方法: 包含【超时、请求数】
     *
     * @param request
     * @param body
     * @param execution
     * @param ex
     * @return
     */
    R blockHandler(HttpRequest request, T body, ClientHttpRequestExecution execution, BlockException ex);

}
