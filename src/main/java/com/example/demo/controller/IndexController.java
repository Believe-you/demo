package com.example.demo.controller;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.demo.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@EnableAutoConfiguration
@RestController
@ConfigurationProperties(prefix = "nm")
public class IndexController {

    @Autowired
    private AsyncService asyncService;

    //    @Value("${nm.name}")
    private String name;
    private String type;
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() { return gender; }

    public void setGender(String gender) {
        this.gender = gender;
    }

    //自定义消息转换器（设置字符编码）
//    @Bean
//    public StringHttpMessageConverter stringHttpMessageConverter() {
//        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("ISO8859-1"));
//        return converter;
//    }

    //使用FastJson解析Json数据
    @Bean
    public HttpMessageConverters fastJsonMessageConverter(){
        //创建FastJson的消息转换器
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //创建FastJson的配置对象
        FastJsonConfig config = new FastJsonConfig();
        //对Json数据进行格式化
        config.setSerializerFeatures(SerializerFeature.PrettyFormat);
        converter.setFastJsonConfig(config);
        HttpMessageConverter<?> con = converter;
        return new HttpMessageConverters(con);
    }

    @RequestMapping("/info")
    public String first() {
        return "赵纪豪" + ":" + name + "," + type + "," + gender;
    }

    @RequestMapping("/async")
    public String asyncTest() throws Exception{
        long start = System.currentTimeMillis();
        Future<String> task1 = asyncService.doTask1();
        Future<String> task2 = asyncService.doTask2();
        Future<String> task3 = asyncService.doTask3();
        while (true){
            if(task1.isDone() && task2.isDone() && task3.isDone()){
                break;
            }
            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        return "全部执行完毕，总耗时："+(end-start)+"毫秒！";
    }
}
