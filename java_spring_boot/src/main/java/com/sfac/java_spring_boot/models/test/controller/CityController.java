package com.sfac.java_spring_boot.models.test.controller;

import com.github.pagehelper.PageInfo;
import com.sfac.java_spring_boot.models.common.vo.Result;
import com.sfac.java_spring_boot.models.common.vo.SearchVo;
import com.sfac.java_spring_boot.models.test.entity.City;
import com.sfac.java_spring_boot.models.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Description CityController
 * @Author HymanHu
 * @Date 2020/8/11 14:12
 */
@RestController
@RequestMapping("/api")
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     * 根据国家ID查询该国家下的所有城市
     * 127.0.0.1/api/cities/522 ---- get
     */
    @GetMapping("/cities/{countryId}")
    public List<City> getCitiesByCountryId(@PathVariable  int countryId) {
        return cityService.getCitiesByCountryId(countryId);
    }





    /**
     * 根据国家ID查询该国家下的所有城市
     * 127.0.0.1/api/cities/522 --- post
     * {"currentPage":"1","pageSize":"5"}
     */
    @PostMapping(value = "/cities/{countryId}", consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVo(@PathVariable int countryId, @RequestBody SearchVo searchVo) {
        return cityService.getCitiesBySearchVo(countryId, searchVo);
    }


    /**
     * 采用排序、模糊查询、等条件来查询数据
     * 127.0.0.1/api/citiesInfo --- post
     * {"currentPage":"1","pageSize":"5","keyWord":"gh","orderBy":"city_id","sort":"desc"}
     */
    @PostMapping(value = "/citiesInfo", consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVoInfo(@RequestBody SearchVo searchVo) {
        System.err.println(searchVo);
        return cityService.getCitiesBySearchVoInfo(searchVo);
    }


    /**
     * 向数据库插入city数据
     * 127.0.0.1/api/insertCity --- post
     * {"cityName":"test1","localCityName":"freeCity","countryId":"522"}
     */
    @PostMapping(value = "/insertCity", consumes = "application/json")
    public Result<City> insertCity(@RequestBody City city) {
        return cityService.insertCity(city);
    }


    /**
     * 根据cityId修改数据库m_city表数据
     * 127.0.0.1/api/updateCity --- Put
     * cityId=2258,cityName="STOP HERE"
     */
    @PutMapping(value = "/updateCity", consumes = "application/x-www-form-urlencoded")
    public Result<City> updateCity(@ModelAttribute City city) {
        return cityService.updateCity(city);
    }


    /**
     * 127.0.0.1/api/deleteCity --- Delete
     * cityId=2258
     */
    @DeleteMapping(value = "/deleteCity", consumes = "application/x-www-form-urlencoded")
    public Result<City> deleteCity(@ModelAttribute City city) {
        return cityService.deleteCity(city);
    }
}
















