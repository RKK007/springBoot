package com.sfac.java_spring_boot.models.test.controller;

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
    /*
    * 127.0.0.1:8080/test/testDesc ---get
    * */
    @GetMapping("/testDesc")
    @ResponseBody
    public String testDesc(){
        return "<center style='color:green;'>This is my first springBoot</center>";
    }
}
