package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableAutoConfiguration
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/save")
    public String save(){
        User user = new User();
        user.setName("小孩子");
        user.setType("1");
        user.setAge(15);
        return userService.saveUser(user);
    }

    @Cacheable(value = "list")
    @RequestMapping("/user/users")
    public List<User> getUserList(){
        System.out.println("-------从数据库中查询-------");
        return userService.userList();
    }

    @Cacheable(value = "user")
    @RequestMapping("/user/user")
    public User getUserById(Integer userId){
        System.out.println("-------从数据库中查询-------");
        return userService.getUserById(userId);
    }

}
