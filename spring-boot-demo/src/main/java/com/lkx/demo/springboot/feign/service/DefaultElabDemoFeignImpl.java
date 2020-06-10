package com.lkx.demo.springboot.feign.service;

import com.lkx.demo.api.model.FeignResponse;
import org.springframework.stereotype.Component;

/**
 * 默认的feign调用失败回调方法
 *
 * @author ： liukx
 * @time ： 2019/6/25 - 15:36
 */
@Component
public class DefaultElabDemoFeignImpl implements ElabDemoFeign {

    @Override
    public FeignResponse<String> getUser() {
        FeignResponse f = new FeignResponse();
        f.setObj("失败回调的方法");
        f.setSuccess(true);
        f.setMessage("失败了,但是没关系.");
        return f;
    }
}
