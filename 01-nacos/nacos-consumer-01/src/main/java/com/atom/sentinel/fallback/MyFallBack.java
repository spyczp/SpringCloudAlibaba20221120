package com.atom.sentinel.fallback;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

public class MyFallBack {

    /**
     * 方法一定要是static方法
     * @param name
     * @param age
     * @return
     */
    public static String myFallBack2(String name, Integer age){
        return "my fall back 2 " + name + ": " + age;
    }


    public static SentinelClientHttpResponse myFallBack(HttpRequest request,
                                                        byte[] body, ClientHttpRequestExecution execution, BlockException ex){

        System.err.println("block: " + ex.getClass().getCanonicalName());

        return new SentinelClientHttpResponse("custom fallbcak info: 针对restTemplate的熔断规则");
    }
}
