package com.lkx.demo.springboot.endpoint;

import com.alibaba.fastjson.JSON;
import com.netflix.servo.DefaultMonitorRegistry;
import com.netflix.servo.monitor.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * eureka端点暴露
 *
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/9/3 - 16:07
 */
@Endpoint(id = "eureka")
@Component
public class EurekaEndpoint {

    @Autowired
    private CustomerEndpoint customerEndpoint;

    @ReadOperation
    public Object get() throws Exception {

        Collection<Monitor<?>> registeredMonitors = DefaultMonitorRegistry.getInstance().getRegisteredMonitors();
        return JSON.toJSONString(registeredMonitors);
    }
}
