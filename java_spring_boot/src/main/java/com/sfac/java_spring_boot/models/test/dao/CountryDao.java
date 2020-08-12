package com.sfac.java_spring_boot.models.test.dao;

import com.sfac.java_spring_boot.models.test.entity.Country;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description CountryDao
 * @Author HymanHu
 * @Date 2020/8/11 13:53
 */
@Repository
@Mapper
public interface CountryDao {

    /*查询国家信息的同时，把这个国家所有的城市查询出，通过国家的ID，此时是一对多的关系，用many*/
    @Select("select * from m_country where country_id = #{countryId}")
    @Results(id = "countryResults", value = {
        @Result(column = "country_id", property = "countryId"),//防止countryId没有值
        @Result(column = "country_id", property = "cities",//第一个参数是需要查询的条件，第二个参数是pojo类对应多一方的参数
                javaType = List.class,//结果类型是List集合
                many = @Many(select = "com.sfac.java_spring_boot.models.test.dao.CityDao.getCitiesByCountryId"))
    })
    Country getCountryByCountryId(int countryId);


    /*一对一的关系*/
    @Select("select * from m_country where country_id = #{countryId}")
    Country getCountryByCountryId2(int countryId);


    @Select("select * from m_country where country_name = #{countryName}")
    @ResultMap(value = "countryResults")
    Country getCountryByCountryName(String countryName);
}
