package com.lkx.demo.springcloud.provider;

import com.lkx.demo.springcloud.provider.feign.DemoFeign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableFeignClients(basePackages = "com.lkx.demo.springcloud.provider.hystix")
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = {DemoFeign.class})
public class SpringCloudProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProviderApplication.class, args);
    }

}
