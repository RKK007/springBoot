package com.sfac.java_spring_boot.models.test.service.impl;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.sfac.java_spring_boot.aspect.ServiceAnnotation;
import com.sfac.java_spring_boot.models.common.vo.Result;
import com.sfac.java_spring_boot.models.common.vo.SearchVo;
import com.sfac.java_spring_boot.models.test.dao.CityDao;
import com.sfac.java_spring_boot.models.test.entity.City;
import com.sfac.java_spring_boot.models.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
/**
 * @Description CityServiceImpl
 * @Author HymanHu
 * @Date 2020/8/11 14:09
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;


    @Override
    @ServiceAnnotation(value = "bbb")//进入切面
    public List<City> getCitiesByCountryId(int countryId) {
        return Optional
                .ofNullable(cityDao.getCitiesByCountryId(countryId))
                .orElse(Collections.emptyList());
    }


    @Override
    public PageInfo<City> getCitiesBySearchVo(int countryId, SearchVo searchVo) {
        searchVo.initSearchVo();//初始化searchVo，当前端没有传值的时候，给定一个初始值
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<City>(Optional.ofNullable(cityDao.getCitiesByCountryId(countryId))
                                          .orElse(Collections.emptyList()));
    }


    /*插入城市信息*/
    @Override
    @Transactional
    public Result<City> insertCity(City city) {
        city.setDateCreated(new Date());
        cityDao.insertCity(city);
        return new Result<>(Result.ResultStatus.SUCCESS.status,"Insert success",city);
    }


    /*修改城市信息*/
    @Override
    @Transactional
    public Result<City> updateCity(City city) {
        cityDao.updateCity(city);
        return new Result<>(Result.ResultStatus.SUCCESS.status,"update success",city);
    }


    /*删除城市信息*/
    @Override
    @Transactional
    public Result<City> deleteCity(City city) {
        cityDao.deleteCity(city);
        return new Result<>(Result.ResultStatus.SUCCESS.status,"delete success",city);
    }


    @Override
    public PageInfo<City> getCitiesBySearchVoInfo(SearchVo searchVo) {
        searchVo.initSearchVo();//初始化searchVo，当前端没有传值的时候，给定一个初始值
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<City>(
                Optional.ofNullable(cityDao.getCitiesBySearchVo(searchVo))
                        .orElse(Collections.emptyList()));
    }
}
