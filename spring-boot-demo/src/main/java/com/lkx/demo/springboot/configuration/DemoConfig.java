package com.lkx.demo.springboot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 创建一个Demo的配置类
 * 在pom文件中新增spring-boot-configuration-processor之后，
 * 打包完成之后META-INF会生成一个spring-configuration-metadata.json
 * 这时候yml文件中会自动提示下面的属性包括描述，中文注释可能会有乱码。
 *
 *
 * @author : liukx
 * @date : 2019/5/31 - 17:08
 */
@ConfigurationProperties(prefix = "demo")
@Component
public class DemoConfig {

    /**
     * 用户名称
     */
    private String username;

    /**
     * 密码
     */
    private String password;


    /**
     * user id default 10000
     */
    private Integer userId = 1000;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
