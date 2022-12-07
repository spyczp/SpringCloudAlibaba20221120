package com.atom.feign.fallbackfactory;

import com.atom.feign.ConsumerToProviderFeign;
import feign.hystrix.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ConsumerToProviderFeignFallBackFactory implements FallbackFactory<ConsumerToProviderFeign> {
    @Override
    public ConsumerToProviderFeign create(Throwable throwable) {
        return new ConsumerToProviderFeign() {
            @Override
            public String test1() {
                return "Fallback工厂：test1的备胎 " + throwable.getMessage();
            }

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
                return null;
            }

            @Override
            public String divide(Integer a, Integer b) {
                return null;
            }
        };
    }
}
