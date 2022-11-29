package com.atom.controller;

import com.atom.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TemplateController {

    @GetMapping("/service/hello")
    public String hello(HttpServletRequest request){
        String header = request.getHeader("X-Request-Id");

        System.out.println("服务提供者1---》/service/hello---》" + header);

        return "provider--->/service/hello";
    }

    @GetMapping("/service/user")
    public User user(){
        System.out.println("服务提供者1---》/service/user");

        User user = new User();
        user.setId(999);
        user.setName("张三");
        user.setPhone("18823456789");

        return user;
    }

    @GetMapping("/service/getUser")
    public User getUser(@RequestParam Integer id, @RequestParam String name, @RequestParam String phone){
        System.out.println("服务提供者1---》/service/getUser");

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPhone(phone);

        return user;
    }

    @PostMapping("/service/addUser")
    public User addUser(@RequestParam Integer id, @RequestParam String name, @RequestParam String phone){
        System.out.println("服务提供者1---》/service/addUser");

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPhone(phone);

        return user;
    }

    @PostMapping("/service/addUser2")
    public User addUser2(@RequestBody User user, @RequestParam String token, @RequestParam String encode){
        System.out.println("服务提供者1---》/service/addUser2");

        System.out.println(user);
        System.out.println(token + "===" + encode);

        return user;
    }
}
