package com.lkx.demo.springcloud.provider.hystrix;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author getj
 * @Date 2020.08.06 11:27
 * @email getj@elab-plus.com
 * @Description:
 */
// , configuration = MyFeignConfiguration.class
@FeignClient(name = "test", url = "${lkx.url}", path = "/", fallback = MyFeignFallback.class)
public interface IMyFeignService {

    @RequestMapping(value = "/index2",  method = RequestMethod.GET)
//    @HystrixCommand(commandProperties = {
//        // 参考 {@link com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager}
//        @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ENABLED, value = "true"),
//        @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "5556"),
//        @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "50"),
//        @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "5000"),
//        @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "THREAD")}, fallbackMethod = "timeOutFallback")
    String index(@RequestParam("sleep") int sleep) throws Exception;
}
