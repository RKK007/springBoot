package com.sfac.java_spring_boot.models.test.controller;

import com.sfac.java_spring_boot.models.test.vo.ApplicationTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
@author:Lenovo
@program:java_spring_boot
@date:2020-08-10 10:39:03
@description:
*/
@Controller
@RequestMapping("/test")
public class TestController {
    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);


    @Value("${server.port}")
    private int port;

    @Value("${com.name}")
    private String name;

    @Value("${com.age}")
    private int age;

    @Value("${com.desc}")
    private String desc;

    @Value("${com.random}")
    private String random;

    @Autowired
    private ApplicationTest at;


    //日志测试
    /**
     * 127.0.0.1:8086/test/logTest ---- get
     */
    @GetMapping("/logTest")
    @ResponseBody
    public String logTest() {
        LOGGER.trace("This is trace log");
        LOGGER.debug("This is debug log");
        LOGGER.info("This is info log");
        LOGGER.warn("This is warn log");
        LOGGER.error("This is error log");
        return "This is log test";
    }



    //系统配置文件测试
/*    127.0.0.1:8086/test/config      */
    @GetMapping("/config")
    @ResponseBody
    public String configTest(){
        return new StringBuffer()
                .append(port).append("----")
                .append(name).append("----")
                .append(age).append("----")
                .append(desc).append("----")
                .append(random).append("----").toString();
    }



    //自定义配置文件测试
    /*    127.0.0.1:8086/test/config2      */
    @GetMapping("/config2")
    @ResponseBody
    public String configTest2(){
        return new StringBuffer()
                .append(at.getPort()).append("----")
                .append(at.getName()).append("----")
                .append(at.getAge()).append("----")
                .append(at.getRandom()).append("----")
                .append(at.getDesc()).append("----").toString();
    }



    //项目启动测试
    /*
    * 127.0.0.1:8086/test/testDesc ---get
    * */
    @GetMapping("/testDesc")
    @ResponseBody
    public String testDesc(){
        return "<center style='color:green;'>This is my first springBoot</center>";
    }
}