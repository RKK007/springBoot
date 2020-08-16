package com.sfac.java_spring_boot.models.test.controller;

import com.sfac.java_spring_boot.models.test.entity.City;
import com.sfac.java_spring_boot.models.test.entity.Country;
import com.sfac.java_spring_boot.models.test.service.CityService;
import com.sfac.java_spring_boot.models.test.service.CountryServcie;
import com.sfac.java_spring_boot.models.test.vo.ApplicationTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

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




    @Autowired
    private CityService cityService;

    @Autowired
    private CountryServcie countryServcie;

    //测试thymeleaf路径
    /*
     * 127.0.0.1/test/thymeleaf ---get
     * */
    @GetMapping("/thymeleaf")
    public String testIndexPage(ModelMap modelMap){
        int countryId = 522;
        List<City> cities = cityService.getCitiesByCountryId(countryId);//根据ID查询city和对应的国家
        cities = cities.stream().limit(10).collect(Collectors.toList());//取前十条记录
        Country country = countryServcie.getCountryByCountryId(countryId);//根据ID查询国家以及该国家下的所有城市

        modelMap.addAttribute("thymeleafTitle", "This thymeleaf replaced");
        modelMap.addAttribute("checked", true);
        modelMap.addAttribute("currentNumber", 99);
        modelMap.addAttribute("changeType", "checkbox");
        modelMap.addAttribute("baiduUrl", "/test/log");
        modelMap.addAttribute("city", cities.get(0));
        modelMap.addAttribute("shopLogo", "http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
        modelMap.addAttribute("shopLogo", "/upload/111.png");
        modelMap.addAttribute("country", country);
        modelMap.addAttribute("cities", cities);
        modelMap.addAttribute("updateCityUri", "/api/city");
        modelMap.addAttribute("template", "test/index");

        //返回外层碎片组装器index.html
        return "index";
    }















}