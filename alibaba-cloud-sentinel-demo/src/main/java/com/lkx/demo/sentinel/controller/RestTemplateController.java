package com.lkx.demo.sentinel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/rest/timeout")
    public String timeOut(@RequestParam(value = "sleep", defaultValue = "5000") Integer sleep) {
        return restTemplate.getForObject("http://localhost:8888/timeOut?sleep=" + sleep, String.class);
    }

    @GetMapping(value = "/rest/error")
    public String error(@RequestParam(value = "sleep", defaultValue = "5000") Integer sleep) {
        return restTemplate.getForObject("http://localhost:8888/timeOut?sleep=" + sleep, String.class);
    }

}
