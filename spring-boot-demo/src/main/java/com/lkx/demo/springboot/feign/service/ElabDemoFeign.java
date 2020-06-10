package com.lkx.demo.springboot.feign.service;

import com.lkx.demo.api.model.FeignResponse;
import com.lkx.demo.springboot.anno.FeignElabClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * feign的接口
 *
 * @author : liukx
 * @date : 2019/4/24 - 16:57
 */
@FeignElabClient(value = "spring-cloud-consumer", path = "/feign", fallback = DefaultElabDemoFeignImpl.class)
public interface ElabDemoFeign {

    @GetMapping(value = "/user")
    public FeignResponse<String> getUser();
}