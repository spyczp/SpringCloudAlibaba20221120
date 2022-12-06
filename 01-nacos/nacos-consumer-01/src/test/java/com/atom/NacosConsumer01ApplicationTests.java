package com.atom;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class NacosConsumer01ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testSentinel(){
        ExecutorService executorService = Executors.newFixedThreadPool(16);

        RestTemplate restTemplate = new RestTemplate();

        while (true){
            try {
                executorService.submit(() -> {
                    String result = restTemplate.getForObject("http://localhost:8083/echo-rest/测试关联规则", String.class);
                    System.out.println(result);
                });

                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
