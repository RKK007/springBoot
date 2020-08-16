package com.sfac.java_spring_boot.models.test.service.impl;

import com.sfac.java_spring_boot.models.test.dao.CountryDao;
import com.sfac.java_spring_boot.models.test.entity.Country;
import com.sfac.java_spring_boot.models.test.service.CountryServcie;
import com.sfac.java_spring_boot.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
/**
 * @Description CountryServcieImpl
 * @Author HymanHu
 * @Date 2020/8/11 13:59
 */
@Service
public class CountryServcieImpl implements CountryServcie {

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Country getCountryByCountryId(int countryId) {
        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }

    @Override
    public Country mograteCountoryByRedis(int countryId) {
        Country country = countryDao.getCountryByCountryId(countryId);

        System.err.println("impl层："+country);

        //存对象到redis
        String countryKey = String.format("country%d",countryId);
        redisUtils.set(countryKey,country);

        return (Country)redisUtils.get(countryKey);
    }



    public static void main(String[] args) {
        int id = 522;
        String countryKey = String.format("country%d",id);

        System.out.println(countryKey);
    }
}
