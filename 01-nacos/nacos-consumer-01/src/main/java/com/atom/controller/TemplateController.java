package com.atom.controller;

import com.atom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ConcurrentHashMap;

@RestController
public class TemplateController {

    @Autowired
    private RestTemplate restTemplate;

    private final String remoteUrl = "http://192.168.30.1:8081";

    @GetMapping("/web/hello")
    public String hello(){

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(remoteUrl + "/service/hello", String.class);

        int statusCodeValue = responseEntity.getStatusCodeValue();
        HttpStatus statusCode = responseEntity.getStatusCode();
        HttpHeaders headers = responseEntity.getHeaders();
        String body = responseEntity.getBody();

        System.out.println(statusCodeValue);
        System.out.println(statusCode);
        System.out.println(headers);
        System.out.println(body);

        return body;
    }


    @GetMapping("/web/user")
    public User user(){
        ResponseEntity<User> responseEntity = restTemplate.getForEntity(remoteUrl + "/service/user", User.class);

        int statusCodeValue = responseEntity.getStatusCodeValue();
        HttpStatus statusCode = responseEntity.getStatusCode();
        HttpHeaders headers = responseEntity.getHeaders();
        User body = responseEntity.getBody();

        System.out.println(statusCodeValue);
        System.out.println(statusCode);
        System.out.println(headers);
        assert body != null;
        System.out.println(body.getId() + "===" + body.getName() + "===" + body.getPhone());

        return body;
    }

    @GetMapping("/web/getUser")
    public User getUser(){

        String[] userArray = {"555", "李四", "13912345678"};

        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        map.put("id", "666");
        map.put("name", "王五");
        map.put("phone", "15898765432");

        ResponseEntity<User> responseEntity1 = restTemplate.getForEntity(remoteUrl + "/service/getUser?id={0}&name={1}&phone={2}", User.class, userArray);
        System.out.println(responseEntity1);

        ResponseEntity<User> responseEntity2= restTemplate.getForEntity(remoteUrl + "/service/getUser?id={id}&name={name}&phone={phone}", User.class, map);
        System.out.println(responseEntity2);

        User body1 = restTemplate.getForObject(remoteUrl + "/service/getUser?id={0}&name={1}&phone={2}", User.class, userArray);
        System.out.println(body1);

        User body2 = restTemplate.getForObject(remoteUrl + "/service/getUser?id={id}&name={name}&phone={phone}", User.class, map);
        System.out.println(body2);

        return body1;
    }

    @GetMapping("/web/addUser")
    public User addUser(){
        String[] userArray = {"777", "孔子", "1235678901"};

        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        map.put("id", "888");
        map.put("name", "孟子");
        map.put("phone", "98765432109");

        LinkedMultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("id", "999");
        multiValueMap.add("name", "墨子");
        multiValueMap.add("phone", "13554323456");

        User user = User.builder()
                .id(1000)
                .name("老子")
                .phone("15635612345")
                .build();


        /*
        传递不了参数
        System.out.println("--------------userArray----------------");
        ResponseEntity<User> responseEntity1 = restTemplate.postForEntity(remoteUrl + "/service/addUser", userArray, User.class);
        System.out.println(responseEntity1);
        System.out.println("------------------------------");*/


        /*
        传递不了参数
        System.out.println("--------------map----------------");
        ResponseEntity<User> responseEntity2 = restTemplate.postForEntity(remoteUrl + "/service/addUser", map, User.class);
        System.out.println(responseEntity2);
        System.out.println("------------------------------");*/


        System.out.println("---------------multiValueMap---------------");
        ResponseEntity<User> responseEntity3 = restTemplate.postForEntity(remoteUrl + "/service/addUser", multiValueMap, User.class);
        System.out.println(responseEntity3);
        System.out.println("------------------------------");


        System.out.println("---------------user---------------");
        ResponseEntity<User> responseEntity4 = restTemplate.postForEntity(remoteUrl + "/service/addUser2?token={token}&encode={encode}", user, User.class, "token20221129", "utf-8");
        System.out.println(responseEntity4);
        System.out.println("------------------------------");


        System.out.println("------------postForObject---multiValueMap----------------");
        User body1 = restTemplate.postForObject(remoteUrl + "/service/addUser", multiValueMap, User.class);
        System.out.println(body1);
        System.out.println("------------------------------");


        System.out.println("----------------postForObject----user----------");
        User body2 = restTemplate.postForObject(remoteUrl + "/service/addUser2?token={token}&encode={encode}", user, User.class, "token20221129", "utf-8");
        System.out.println(body2);
        System.out.println("------------------------------");


        System.out.println("--------------userJson----------------");
        String userJson = "{\n" +
                "  \"id\": 1111,\n" +
                "  \"name\": \"庄子\",\n" +
                "  \"phone\": \"16924664780\"\n" +
                "}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(userJson, headers);
        User body3 = restTemplate.postForObject(remoteUrl + "/service/addUser2?token={token}&encode={encode}", httpEntity, User.class, "token20221129", "utf-8");
        System.out.println(body3);
        System.out.println("------------------------------");


        return body2;
    }

}
