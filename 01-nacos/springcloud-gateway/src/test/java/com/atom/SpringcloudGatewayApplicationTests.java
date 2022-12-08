package com.atom;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;

@SpringBootTest
class SpringcloudGatewayApplicationTests {

    /**
     * 生成时间，用于路由转发规则中
     */
    @Test
    void getTime() {
        System.out.println(ZonedDateTime.now());
    }

}
