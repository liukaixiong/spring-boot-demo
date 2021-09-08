package com.lkx.demo.sentinel.ext.fallback;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liukx
 */
public class RestFallBackFactory implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    private Map<String, URLRestFallback> urlRestFallbackCacheMap = new HashMap<>();

    /**
     * 匹配默认的失败回调方法
     */
    private URLRestFallback defaultRestFallback;

    public void setDefaultRestFallback(URLRestFallback defaultRestFallback) {
        this.defaultRestFallback = defaultRestFallback;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取降级处理器
     *
     * @param url
     * @returnNon-parseable
     */
    public URLRestFallback getFallbackByUrl(String url) {
        return urlRestFallbackCacheMap.getOrDefault(url, this.defaultRestFallback);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, URLRestFallback> urlRestFallbackMap = this.applicationContext.getBeansOfType(URLRestFallback.class);
        if (urlRestFallbackMap != null && urlRestFallbackMap.size() > 0) {
            urlRestFallbackMap.forEach((K, V) -> {
                List<String> urlList = V.url();
                for (int i = 0; i < urlList.size(); i++) {
                    String url = urlList.get(i);
                    urlRestFallbackCacheMap.put(url, V);
                }
            });
        }
    }
}
