package com.lkx.demo.sentinel.utils;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.lkx.demo.sentinel.ext.fallback.RestFallBackFactory;
import com.lkx.demo.sentinel.ext.fallback.URLRestFallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

public class FallbackUtils {

    private static Logger logger = LoggerFactory.getLogger(FallbackUtils.class);

    /**
     * 降级逻辑
     *
     * @param request
     * @param body
     * @param execution
     * @param ex
     * @return
     */
    public static SentinelClientHttpResponse fallback(HttpRequest request, byte[] body,
        ClientHttpRequestExecution execution, BlockException ex) throws Exception {

        URLRestFallback fallbackByUrl = getUrlRestFallback(request);

        if (fallbackByUrl == null) {
            logger.warn("降级失败", ex.getRule());
            throw ex;
        }

        Object requestParam = getRequestBody(body, fallbackByUrl.getClass());

        logger.warn("触发降级方法: " + fallbackByUrl);

        Object result = fallbackByUrl.fallback(request, requestParam, execution, ex);

        return new SentinelClientHttpResponse(JSON.toJSONString(result));
    }

    public static SentinelClientHttpResponse blockHandler(HttpRequest request, byte[] body,
        ClientHttpRequestExecution execution, BlockException ex) throws Exception {

        URLRestFallback fallbackByUrl = getUrlRestFallback(request);

        if (fallbackByUrl == null) {
            logger.warn("降级失败", ex.getRule());
            throw ex;
        }

        Object requestParam = getRequestBody(body, fallbackByUrl.getClass());

        logger.warn("触发熔断方法: " + fallbackByUrl);

        Object result = fallbackByUrl.blockHandler(request, requestParam, execution, ex);

        return new SentinelClientHttpResponse(JSON.toJSONString(result));
    }

    private static Object getRequestBody(byte[] body, Class<?> clazz) {
        Class<?> requestType = ClassUtil.getTypeArgument(clazz, 0);
        Object requestParam = null;
        if (requestType == String.class) {
            requestParam = new String(body);
        } else {
            requestParam = JSON.parseObject(body, requestType);
        }
        return requestParam;
    }

    private static URLRestFallback getUrlRestFallback(HttpRequest request) {
        RestFallBackFactory restFallBackFactory = SpringUtil.getBean(RestFallBackFactory.class);

        String path = request.getURI().getPath();

        URLRestFallback fallbackByUrl = restFallBackFactory.getFallbackByUrl(path);
        return fallbackByUrl;
    }
}