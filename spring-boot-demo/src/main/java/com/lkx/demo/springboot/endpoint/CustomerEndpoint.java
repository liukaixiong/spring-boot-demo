package com.lkx.demo.springboot.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过访问http://localhost:9876/actuator/myMap即可暴露数据
 *
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/9/2 - 15:17
 */
@Endpoint(id = "myRead")
@Component
public class CustomerEndpoint {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private Map<String, String> data = new HashMap<>();

    public CustomerEndpoint() {
        data.put("A", "lkx");
        data.put("B", "jay");
        data.put("C", "jj");
        data.put("D", "vae");
    }

    @ReadOperation
    public Object beans() {
        return data;
    }

    /**
     * 注意这里是POST请求 @Nullable 代表可为空,JSON 参数
     *
     * @param body
     * @param body2
     * @return
     */
    @WriteOperation
    public String write(String body, @Nullable String body2) {
        logger.info("body - %s %s " + body);
        return body;
    }

}
