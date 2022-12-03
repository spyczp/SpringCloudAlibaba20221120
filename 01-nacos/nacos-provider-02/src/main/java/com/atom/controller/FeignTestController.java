package com.atom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignTestController {

    @GetMapping("/test1")
    public String test1(){
        return "test1---2";
    }
}
