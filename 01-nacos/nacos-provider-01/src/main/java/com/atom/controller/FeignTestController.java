package com.atom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FeignTestController {

    @GetMapping("/test1")
    public String test1(HttpServletRequest request){

        System.out.println(request.getHeader("X-Request-Id"));

        return "test1---1";
    }
}
