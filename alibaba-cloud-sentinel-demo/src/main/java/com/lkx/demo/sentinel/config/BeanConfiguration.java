package com.lkx.demo.sentinel.config;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.lkx.demo.sentinel.ext.fallback.DefaultRestFallBack;
import com.lkx.demo.sentinel.ext.fallback.RestFallBackFactory;
import com.lkx.demo.sentinel.utils.FallbackUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(value = {SpringUtil.class})
public class BeanConfiguration {

    @Bean
    @SentinelRestTemplate(fallback = "fallback", fallbackClass = FallbackUtils.class, blockHandler = "blockHandler", blockHandlerClass = FallbackUtils.class)
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(3000);
        simpleClientHttpRequestFactory.setReadTimeout(5000);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(simpleClientHttpRequestFactory);
        return restTemplate;
    }

    @Bean
    public RestFallBackFactory restFallBackFactory() {
        RestFallBackFactory restFallBackFactory = new RestFallBackFactory();
        restFallBackFactory.setDefaultRestFallback(defaultRestFallBack());
        return restFallBackFactory;
    }

    @Bean
    public DefaultRestFallBack defaultRestFallBack() {
        return new DefaultRestFallBack();
    }
}
