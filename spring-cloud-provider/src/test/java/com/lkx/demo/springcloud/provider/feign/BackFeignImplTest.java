package com.lkx.demo.springcloud.provider.feign;

import com.alibaba.fastjson.JSON;
import com.lkx.demo.api.model.FeignResponse;
import com.lkx.demo.springcloud.provider.SpringCloudProviderApplicationTests;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BackFeignImplTest extends SpringCloudProviderApplicationTests {

    @Autowired
    private DemoFeign demoFeign;

    private Logger logger = LoggerFactory.getLogger(BackFeignImplTest.class);

    @Test
    public void getUser() {
        FeignResponse<String> user = demoFeign.getUser();
        System.out.println(" 返回结果 : " + JSON.toJSONString(user));
    }
}