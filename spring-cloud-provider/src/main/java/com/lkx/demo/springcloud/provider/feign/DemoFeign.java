package com.lkx.demo.springcloud.provider.feign;

import com.lkx.demo.api.model.FeignResponse;
import com.lkx.demo.springcloud.provider.feign.fallback.BackFeignImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * feign的接口
 *
 * @author : liukx
 * @date : 2019/4/24 - 16:57
 */
@FeignClient(value = "spring-cloud-consumer", path = "/feign", fallback = BackFeignImpl.class)
public interface DemoFeign {

    @GetMapping(value = "/user")
    public FeignResponse<String> getUser();
}