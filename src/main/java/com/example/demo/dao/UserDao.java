package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String addUser(User user){
        int update = jdbcTemplate.update("insert into user(name,type,age) values (?,?,?)",
                new Object[]{user.getName(), user.getType(), user.getAge()});
        if(update == 1){
            return "SUCCESS";
        }
        return null;
    }
}
