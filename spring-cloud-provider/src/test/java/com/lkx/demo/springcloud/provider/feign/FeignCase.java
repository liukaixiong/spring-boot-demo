package com.lkx.demo.springcloud.provider.feign;

import com.lkx.demo.springcloud.provider.hystrix.*;
import com.lkx.demo.springcloud.provider.utils.ThreadLocalUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyFeignConfiguration.class, HttpMessageConvertersAutoConfiguration.class,
    MyFeignFallback.class, FeignAutoConfiguration.class, HystrixMethodInvoke.class, MyHystrixHooK.class,
    FeignHystrixConcurrencyStrategy.class, IMyFeignService.class})
@EnableFeignClients(basePackageClasses = {IMyFeignService.class, IHttpsService.class})
@EnableHystrix
@EnableAspectJAutoProxy
public class FeignCase {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IMyFeignService myFeignService;

    @Autowired
    IHttpsService httpsService;

    @Autowired
    HystrixMethodInvoke invoke;

    @Test
    public void testHttp() throws Exception {
        String index = myFeignService.index(6000);
        logger.info(index);
        Thread.sleep(10000);
    }

    @Test
    public void testHttps() throws Exception {
        String body =
            "{\"brandId\":1,\"houseId\":\"10080\",\"reporterMobile\":\"18229180353\",\"inheritLogic\":4,\"terminal\":\"1\"}";
        String index = httpsService.index(body);
        System.out.println(index);
    }

    @Test
    public void testAnno() throws Exception {
        logger.info("开始测试");
        ThreadLocalUtils.set("A");
        String s = invoke.timeOut(6000);
        Thread.sleep(10000);
    }

    @Test
    public void testError() throws Exception {
        logger.info("开始测试");
        // 测试上下文中包含的值是否能够传递到下一个线程
        ThreadLocalUtils.set("A");
        String s = invoke.error(6000);
        Thread.sleep(10000);
    }

    @Test
    public void testAnnoAsync() throws Exception {
        String s = invoke.timeOutFallback(6000);
        logger.info("得到的最终结果: " + s);
        Thread.sleep(10000);
    }

}
