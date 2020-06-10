package com.lkx.demo.springboot.feign;

import com.lkx.demo.springboot.factory.ElabFeignClientFactoryBean;
import feign.Feign;
import feign.Target;
import org.springframework.cloud.openfeign.FeignContext;

/**
 * @author Spencer Gibb
 */
public interface Targeter {

    <T> T target(ElabFeignClientFactoryBean factory, Feign.Builder feign,
                 FeignContext context, Target.HardCodedTarget<T> target);

}