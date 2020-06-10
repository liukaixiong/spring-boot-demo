package com.lkx.demo.springboot;

import com.lkx.demo.springboot.anno.EnableElabFeign;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@EnableAdminServer
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@SpringBootApplication
//@EnableDiscoveryClient
@EnableElabFeign(value = {"com.lkx.demo.springboot.feign.service"})
public class SpringCloudAdminApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringCloudAdminApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAdminApplication.class, args);
    }

}
