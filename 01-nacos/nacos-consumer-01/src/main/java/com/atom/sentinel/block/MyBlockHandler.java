package com.atom.sentinel.block;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

public class MyBlockHandler {

    public static SentinelClientHttpResponse blockA(HttpRequest request,
                                                    byte[] body, ClientHttpRequestExecution execution, BlockException ex){
        System.err.println("block: " + ex.getClass().getCanonicalName());

        return new SentinelClientHttpResponse("custom block info: 针对restTemplate的限流规则");
    }
}
