package com.atom.controller;

import com.atom.model.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private Human human;

    @GetMapping("getInfo")
    public String getInfo(){
        return human.getName() + "===" + human.getAge() + "===" + human.getHeight();
    }
}
