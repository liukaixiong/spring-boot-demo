package com.lkx.demo.sentinel.controller;

import com.alibaba.fastjson.JSONObject;
import com.lkx.demo.sentinel.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TestService service;

    @GetMapping(value = "/hello/{name}")
    public String apiHello(@PathVariable String name) {
        return service.sayHello(name);
    }

    @GetMapping(value = "/timeOut")
    public String timeOut(@RequestParam("sleep") Integer sleep) throws Exception {
        Thread.sleep(sleep);
        JSONObject json = new JSONObject();
        json.put("success", "OK");
        logger.info("结果: " + json.toJSONString());
        return json.toJSONString();
    }

    @GetMapping(value = "/error")
    public String timeOut() throws Exception {
        int i = 1 / 0;
        return "NO";
    }

    @GetMapping(value = "/errorPage")
    public String errorPage() throws Exception {
        return "看什么看,出问题了.";
    }
}