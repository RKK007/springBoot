package com.sfac.java_spring_boot.models.test.service;

import com.github.pagehelper.PageInfo;
import com.sfac.java_spring_boot.models.common.vo.Result;
import com.sfac.java_spring_boot.models.common.vo.SearchVo;
import com.sfac.java_spring_boot.models.test.entity.City;


import java.util.List;
import java.util.Map;

/**
 * @Description CityService
 * @Author HymanHu
 * @Date 2020/8/11 14:09
 */
public interface CityService {

    List<City> getCitiesByCountryId(int countryId);

    PageInfo<City> getCitiesBySearchVo(int countryId, SearchVo searchVo);

    Result<City> insertCity(City city);

    Result<City> updateCity(City city);

    Result<City> deleteCity(City city);

    PageInfo<City> getCitiesBySearchVoInfo(SearchVo searchVo);
}



















