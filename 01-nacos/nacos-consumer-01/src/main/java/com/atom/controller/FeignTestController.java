package com.atom.controller;

import com.atom.feign.ConsumerToProviderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignTestController {

    @Autowired
    @Qualifier("com.atom.feign.ConsumerToProviderFeign")
    private ConsumerToProviderFeign consumerToProviderFeign;

    @GetMapping("/testOne")
    public String testOne(){
        String result = consumerToProviderFeign.test1();
        return result;
    }
}
