package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    String saveUser(User user);

    List<User> userList();

    User getUserById(Integer userId);
}
