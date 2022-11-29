package com.atom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfigBApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigBApplication.class, args);
    }

}