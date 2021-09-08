package com.lkx.demo.springcloud.provider.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyFeignFallback implements IMyFeignService, IHttpsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String index(int sleep) throws Exception {
        logger.warn("触发了MyFeignService的失败回调 : ");
        return "尽管失败了,但是我还是希望你能成功.";
    }

    @Override
    public String index(String body) throws Exception {
        logger.warn("111触发了MyFeignService的失败回调");
        return "111尽管失败了,但是我还是希望你能成功.";
    }
}
