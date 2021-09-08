package com.lkx.demo.springcloud.provider.hystrix;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "my-https-test", url = "https://dm-api.elab-plus.cn/elab-marketing-user/", path = "/", fallback = MyFeignFallback.class)
public interface IHttpsService {

    @RequestMapping(value = "/bindAdviser/reportAdviserListNew", method = RequestMethod.POST, produces = "application/json;charset=UTF-8",consumes = {"application/json;charset=UTF-8"})
    String index(@RequestBody String body) throws Exception;

}
