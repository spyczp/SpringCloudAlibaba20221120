package com.atom.controller;

import com.atom.feign.ConsumerToProviderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FeignTestController {

    @Autowired
    @Qualifier("com.atom.feign.ConsumerToProviderFeign")
    private ConsumerToProviderFeign consumerToProviderFeign;

    @GetMapping("/testOne")
    public String testOne(HttpServletRequest request){

//        System.out.println("X-Request-Id=" + request.getHeader("X-Request-Id"));
//
//        //打印网关过滤器中 添加的请求头数据
//        System.out.println("X-Request-red=" + request.getHeader("X-Request-red"));

        //打印网关过滤器中 添加的参数
//        System.out.println("color=" + request.getParameter("color"));

        String result = consumerToProviderFeign.test1();
        return result;
    }
}
