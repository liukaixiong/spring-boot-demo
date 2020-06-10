package com.lkx.demo.springboot.conversionService;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;

/**
 * 案例转换器
 *
 * @author ： liukx
 * @time ： 2019/6/3 - 11:49
 */
@Component
public class DemoConversionService implements ConversionService {

    @Override
    public boolean canConvert(Class<?> aClass, Class<?> aClass1) {
        return false;
    }

    @Override
    public boolean canConvert(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor1) {
        return false;
    }

    @Override
    public <T> T convert(Object o, Class<T> aClass) {
        return null;
    }

    @Override
    public Object convert(Object o, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor1) {
        return null;
    }
}
