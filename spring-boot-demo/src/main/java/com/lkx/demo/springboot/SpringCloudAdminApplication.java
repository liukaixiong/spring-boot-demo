package com.lkx.demo.springboot;

import com.lkx.demo.springboot.anno.EnableElabFeign;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//@EnableAdminServer
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@SpringBootApplication
@EnableDiscoveryClient
@EnableElabFeign(value = {"com.lkx.demo.springboot.feign.service"})
public class SpringCloudAdminApplication extends SpringBootServletInitializer implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringCloudAdminApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAdminApplication.class, args);
    }

}
