package com.lkx.demo.springcloud.provider.feign;

import feign.hystrix.FallbackFactory;

public class MyFallbackFactory implements FallbackFactory<String> {

    @Override
    public String create(Throwable cause) {
        return null;
    }

}
