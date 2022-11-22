package com.atom.controller;

import com.atom.model.Human;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private Human human;

    @GetMapping("getInfo")
    public String getInfo(){
        return human.getName() + "===" + human.getAge() + "===" + human.getHeight();
    }
}
