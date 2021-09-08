package com.lkx.demo.springboot.configuration;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * bean的基础配置
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/9/3 - 16:27
 */
@Configuration
@Import({SpringUtil.class})
public class BeanConfig {

}
