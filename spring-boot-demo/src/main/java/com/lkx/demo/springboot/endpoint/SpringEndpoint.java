package com.lkx.demo.springboot.endpoint;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSON;
import com.lkx.demo.springboot.endpoint.obj.MessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/9/3 - 16:40
 */
@Endpoint(id = "spring")
@Component
public class SpringEndpoint {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @WriteOperation
    public Object get(String clazzName, String fieldName) {

        MessageHelper.getINSTANCE().add("abc1");
        MessageHelper.getINSTANCE().add("abc2");
        MessageHelper.getINSTANCE().add("abc3");
        MessageHelper.getINSTANCE().add("abc4");

        try {
            Class<?> aClass = Class.forName(clazzName);
            Object bean = getInternalObject(aClass);
            Field data = aClass.getDeclaredField(fieldName);
            data.setAccessible(true);
            Object o = data.get(bean);
            System.out.println("打印值: " + o);
            if (o instanceof String || o instanceof Number) {
                return o;
            }
            return JSON.toJSONString(o);
        } catch (Exception e) {
            logger.error("处理异常", e);
            return null;
        }
    }

    public Object getInternalObject(Class<?> aClass) {
        return MessageHelper.getINSTANCE();
    }

    private Object getSpringObject(Class<?> aClass) {
        return SpringUtil.getBean(aClass);
    }
}
