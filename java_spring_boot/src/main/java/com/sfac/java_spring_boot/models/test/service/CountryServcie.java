package com.sfac.java_spring_boot.models.test.service;


import com.sfac.java_spring_boot.models.test.entity.Country;

/**
 * @Description CountryServcie
 * @Author HymanHu
 * @Date 2020/8/11 13:59
 */
public interface CountryServcie {

    Country getCountryByCountryId(int countryId);

    Country getCountryByCountryName(String countryName);

    //读取存到redis，再通过redis读取
    Country mograteCountoryByRedis(int countryId);

}
