package com.lkx.demo.springboot.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/9/2 - 15:17
 */
@Endpoint(id = "my-read")
@Component
public class CustomerEndpoint {

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

}
