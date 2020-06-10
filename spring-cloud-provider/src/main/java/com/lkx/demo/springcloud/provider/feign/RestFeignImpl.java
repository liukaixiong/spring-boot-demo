//package com.lkx.demo.springcloud.provider.feign;
//
//import com.lkx.demo.api.model.FeignResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * feign的demo
// *
// * @author ： liukx
// * @time ： 2019/4/24 - 17:01
// */
//@RestController
//@RequestMapping("/feign")
//public class RestFeignImpl implements DemoFeign {
//
//    private Logger logger = LoggerFactory.getLogger(RestFeignImpl.class);
//
//    @Override
//    @GetMapping(value = "/user")
//    @ResponseBody
//    public FeignResponse<String> getUser() {
//        FeignResponse<String> response = new FeignResponse<String>();
//        response.setSuccess(true);
//        response.setObj("cccccccccc");
//        return response;
//    }
//
//}
