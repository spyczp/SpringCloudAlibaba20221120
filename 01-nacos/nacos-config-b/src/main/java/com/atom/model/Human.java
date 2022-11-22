package com.atom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@RefreshScope
public class Human {

    @Value("${human.name}")
    private String name;

    @Value("${human.age}")
    private String age;

    @Value("${human.height}")
    private String height;
}
