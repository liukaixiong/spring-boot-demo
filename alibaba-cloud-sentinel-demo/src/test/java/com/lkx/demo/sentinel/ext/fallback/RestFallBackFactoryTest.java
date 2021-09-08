package com.lkx.demo.sentinel.ext.fallback;

import cn.hutool.core.util.ClassUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestFallBackFactory.class, DefaultRestFallBack.class, HttpMessageConvertersAutoConfiguration.class})
public class RestFallBackFactoryTest extends TestCase {

    @Autowired
    private RestFallBackFactory restFallBackFactory;

    @Autowired
    private HttpMessageConverter httpMessageConverter;

    @Test
    public void testRest() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success","true");
        String body = "OK!";
        URLRestFallback fallbackByUrl = restFallBackFactory.getFallbackByUrl("http://localhost:8888/timeOut");
        byte[] bodyBytes = body.getBytes();
        Class<?> requestType = ClassUtil.getTypeArgument(fallbackByUrl.getClass(), 0);
        Object requestParam = JSON.parseObject(bodyBytes, requestType);
//        ObjectMapper objectMapper = new ObjectMapper();
//        Object o = objectMapper.readValue(bodyBytes, requestType);
        //Object fallback = fallbackByUrl.fallback(null, requestParam, null, null);
        System.out.println(requestParam);
    }
}