package com.atom.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atom.feign.ConsumerToProviderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Qualifier("com.atom.feign.ConsumerToProviderFeign")
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

    @GetMapping("/testDivide")
    public String testDivide(@RequestParam("a") Integer a, @RequestParam("b") Integer b){
        String result = consumerToProviderFeign.divide(a, b);
        return result;
    }

    @GetMapping("/testDivide2")
    public String testDivide2(@RequestParam("a") Integer a){
        String result = consumerToProviderFeign.divide(a);
        return result;
    }

    @GetMapping("/services/{service}")
    public Object client(@PathVariable String service){
        return discoveryClient.getInstances(service);
    }

    @GetMapping("/services")
    public Object services(){
        System.out.println(discoveryClient.description());
        System.out.println(discoveryClient.getOrder());
        return discoveryClient.getServices();
    }

    /**
     * 降级测试
     * @return
     */
    @GetMapping("/degradeTest")
    public String degradeTest(){

        //Thread.sleep(250);
        int s = 10 / 0;

        return "降级测试-正常页面";
    }

    /**
     * 热点限流测试
     * @return
     */
    @GetMapping("/paramFlowTest")
    @SentinelResource("paramFlowTest")
    public String paramFlowTest(@RequestParam String name, @RequestParam Integer age){
        return name + ": " + age;
    }

}
