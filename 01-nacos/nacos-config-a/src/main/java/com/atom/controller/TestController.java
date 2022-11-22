package com.atom.controller;

import com.atom.model.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private Human human;

    @GetMapping("getHuman")
    public String getHuman(){
        return human.getName() + "---" + human.getAge() + "---" + human.getHeight();
    }
}
