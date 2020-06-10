package com.lkx.demo.springcloud.provider.feign.rollback;

import com.lkx.demo.api.model.FeignResponse;
import com.lkx.demo.springcloud.provider.feign.DemoFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * feign的demo
 *
 * @author ： liukx
 * @time ： 2019/4/24 - 17:01
 */
@Service
public class BackFeignImpl implements DemoFeign {

    private Logger logger = LoggerFactory.getLogger(BackFeignImpl.class);

    @Override
    public FeignResponse<String> getUser() {
        FeignResponse<String> response = new FeignResponse<String>();
        response.setSuccess(true);
        response.setObj("bbbbbbbbbbbbbbb");
        logger.error("触发失败回调接口 ... ");
        return response;
    }

}