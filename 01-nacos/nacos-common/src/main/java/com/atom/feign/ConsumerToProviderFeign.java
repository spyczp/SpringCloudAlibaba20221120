package com.atom.feign;

import com.atom.feign.hystrix.ConsumerToProviderFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "nacos-provider", fallback = ConsumerToProviderFeignHystrix.class)
public interface ConsumerToProviderFeign {

    @GetMapping("/test1")
    public String test1();

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

    /**
     * feign声明的接口可以有默认实现，就是可以不需要远程服务提供者实现，自己实现了。
     * @param a
     * @return
     */
    default String divide(@RequestParam("a") Integer a){
        System.out.println("consumer divide method......");
        return divide(a, 1);
    }
}
