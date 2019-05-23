package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserExample;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public String saveUser(User user){
        int insert = userMapper.insert(user);
        if(insert == 1){
            return "SUCCESS";
        }
        return null;
    }

    @Override
    public List<User> userList() {
        UserExample example = new UserExample();
        return userMapper.selectByExample(example);
    }

    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }


}
