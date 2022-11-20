package com.atom.controller;

import com.atom.feign.ConsumerToProviderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private ConsumerToProviderFeign consumerToProviderFeign;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/echo/{app}")
    public String echoAppName(@PathVariable("app") String app){
        //使用LoadBalancerClient和RestTemplate结合的方式来访问
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");

        //url == > http://192.168.1.12:8081/echo/{app}
        String url = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), app);
        System.out.println("请求的url地址是：" + url);

        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/echo-rest/{str}")
    public String rest(@PathVariable("str") String str){
        return restTemplate.getForObject("http://nacos-provider/echo/" + str, String.class);
    }

    @GetMapping("/index")
    public String index(){
        return restTemplate.getForObject("http://nacos-provider/", String.class);
    }

    @GetMapping("/test")
    public String test(){
        return restTemplate.getForObject("http://nacos-provider/test", String.class);
    }

    @GetMapping("/callThroughFeign/{msg}")
    public String callThroughFeign(@PathVariable("msg") String msg){
        return consumerToProviderFeign.echo(msg);
    }

}