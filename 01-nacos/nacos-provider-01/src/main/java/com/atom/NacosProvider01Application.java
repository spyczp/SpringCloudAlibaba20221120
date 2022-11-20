package com.atom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosProvider01Application {

    public static void main(String[] args) {
        SpringApplication.run(NacosProvider01Application.class, args);
    }

}
