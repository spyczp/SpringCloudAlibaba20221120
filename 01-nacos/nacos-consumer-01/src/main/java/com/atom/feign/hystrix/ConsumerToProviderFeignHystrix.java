package com.atom.feign.hystrix;

import com.atom.feign.ConsumerToProviderFeign;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ConsumerToProviderFeignHystrix implements ConsumerToProviderFeign {
    @Override
    public ResponseEntity index() {
        return null;
    }

    @Override
    public ResponseEntity test() {
        return null;
    }

    @Override
    public ResponseEntity sleep() {
        return null;
    }

    @Override
    public String echo(String string) {
        return "我是备胎：echo";
    }

    @Override
    public String divide(Integer a, Integer b) {
        return "我是备胎：divide";
    }
}
