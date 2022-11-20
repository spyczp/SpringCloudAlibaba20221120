package com.atom.feign;

import com.atom.feign.hystrix.ConsumerToProviderFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "nacos-provider", fallback = ConsumerToProviderFeignHystrix.class)
public interface ConsumerToProviderFeign {

    @GetMapping("/")
    public ResponseEntity index();

    @GetMapping("/test")
    public ResponseEntity test();

    @GetMapping("/sleep")
    public ResponseEntity sleep();

    @GetMapping("/echo/{string}")
    public String echo(@PathVariable("string") String string);

    @GetMapping("/divide")
    public String divide(@RequestParam("a") Integer a, @RequestParam("b") Integer b);
}
