package com.sfac.java_spring_boot.models.test.controller;

import com.sfac.java_spring_boot.models.test.entity.Country;
import com.sfac.java_spring_boot.models.test.service.CountryServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description CountryController
 * @Author HymanHu
 * @Date 2020/8/11 14:01
 */
@RestController
@RequestMapping("/api")
public class CountryController {

    @Autowired
    private CountryServcie countryServcie;


    /**
     * 127.0.0.1/api/country/522 ---- get
     *根据国家ID获取国家信息
     */
    @GetMapping("/country/{countryId}")
    public Country getCountryByCountryId(@PathVariable int countryId) {
        return countryServcie.getCountryByCountryId(countryId);
    }



    /**
     * 127.0.0.1/api/country?countryName=China ---- get
     * 根据国家的名字来获取国家信息
     */
    @GetMapping("/country")
    public Country getCountryByCountryName(@RequestParam String countryName) {
        return countryServcie.getCountryByCountryName(countryName);
    }
}
