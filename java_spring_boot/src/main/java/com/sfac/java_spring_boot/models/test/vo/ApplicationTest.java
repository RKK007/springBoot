package com.sfac.java_spring_boot.models.test.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
/*
@author:Lenovo
@program:java_spring_boot
@date:2020-08-10 15:52:03
@description:
*/
@Component//注册为主键
@PropertySource("classpath:config/applicationTest.properties")
@ConfigurationProperties(prefix = "com.qq")//公共的前缀，使用了这个前缀就不再使用@Value注解了
public class ApplicationTest {

    private int port;


    private String name;


    private int age;


    private String desc;


    private String random;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }
}
