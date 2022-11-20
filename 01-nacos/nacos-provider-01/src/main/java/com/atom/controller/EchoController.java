package com.atom.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class EchoController {

    @GetMapping("/")
    public ResponseEntity index(){
        log.info("provider /");
        return new ResponseEntity("index", HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity test(){
        log.info("provider /test");
        return new ResponseEntity("test", HttpStatus.OK);
    }

    @GetMapping("/sleep")
    public ResponseEntity sleep(){
        log.info("provider /sleep");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResponseEntity("sleep", HttpStatus.OK);
    }

    @GetMapping("/echo/{string}")
    public String echo(@PathVariable("string") String string){
        log.info("provider /echo/{string}");
        return "Hello Nacos Discovery " + string;
    }

    @GetMapping("/divide")
    public String divide(@RequestParam("a") Integer a, @RequestParam("b") Integer b){
        log.info("provider /divide");
        return String.valueOf(a / b);
    }
}
