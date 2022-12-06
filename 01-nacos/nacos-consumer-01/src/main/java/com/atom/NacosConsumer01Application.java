package com.atom;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.atom.sentinel.block.MyBlockHandler;
import com.atom.sentinel.fallback.MyFallBack;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class NacosConsumer01Application {

    @SentinelRestTemplate(blockHandler = "blockA",
                            blockHandlerClass = MyBlockHandler.class,
                            fallback = "myFallBack",
                            fallbackClass = MyFallBack.class)
    @LoadBalanced //这里除了开启负载均衡外，还把restTemplate对象交给ribbon来管理
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumer01Application.class, args);
    }

}
