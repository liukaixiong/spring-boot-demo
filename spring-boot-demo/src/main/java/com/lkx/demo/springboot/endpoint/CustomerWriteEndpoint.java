package com.lkx.demo.springboot.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/9/2 - 16:36
 */
@Endpoint(id = "my-write")
@Component
public class CustomerWriteEndpoint {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @WriteOperation
    public String write(String body, @Nullable String body2) {
        logger.info("body - %s %s " + body);
        return body;
    }

}
