package com.example.demo;

import com.example.demo.controller.IndexController;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private IndexController indexController;

    @Test
    public void contextLoads() {
        TestCase.assertEquals(this.indexController.first(),"SpringBoot:yourname,yourstring,sb");
    }

}
