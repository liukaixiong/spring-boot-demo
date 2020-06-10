package com.lkx.demo.springboot.controllers;

import com.lkx.demo.api.model.FeignResponse;
import com.lkx.demo.springboot.feign.service.ElabDemoFeign;
import com.lkx.demo.springboot.model.DemoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.lkx.demo.springboot.secure.SampleService;

/**
 * 请求层
 *
 * @author ： liukx
 * @time ： 2019/6/3 - 13:46
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    private Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private ElabDemoFeign feign;

    @RequestMapping(value = "/req", method = RequestMethod.POST)
    @ResponseBody
    public String demo(@RequestBody DemoRequest request) {
        FeignResponse<String> user = feign.getUser();
        logger.info("得到的请求结果 : " + request.toString() + " \t" + user.toString());
        return "OK";
    }

//    @RequestMapping(value = "/secure", method = RequestMethod.POST)
//    @ResponseBody
//    public String secure(@RequestBody DemoRequest request) {
//        sampleService.secure();
//        sampleService.authorized();
//        sampleService.denied();
//        return "OK";
//    }
}
