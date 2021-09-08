package com.lkx.demo.sentinel.ext.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

import java.util.Arrays;
import java.util.List;

public class DefaultRestFallBack implements URLRestFallback<String, String> {

    @Override
    public List<String> url() {
        return Arrays.asList("http://localhost:8888/timeOut");
    }

    @Override
    public String fallback(HttpRequest request, String body, ClientHttpRequestExecution execution, BlockException ex) {
        return "降级成功!";
    }

    @Override
    public String blockHandler(HttpRequest request, String body, ClientHttpRequestExecution execution,
        BlockException ex) {
        return "熔断成功!";
    }
}
