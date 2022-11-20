package com.atom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosProvider02Application {

    public static void main(String[] args) {
        SpringApplication.run(NacosProvider02Application.class, args);
    }

}
