package com.sfac.java_spring_boot.models.test.dao;

import com.sfac.java_spring_boot.models.common.vo.SearchVo;
import com.sfac.java_spring_boot.models.test.entity.City;
import com.sfac.java_spring_boot.models.test.entity.Country;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @Description CityDao
 * @Author HymanHu
 * @Date 2020/8/11 14:08
 */
@Repository
@Mapper
public interface CityDao {

    /*普通查询--------根据国家的ID，查询所有的城市信息，每一个城市属于一个国家，一对一关系*/
    @Select("select * from m_city where country_id = #{countryId}")
    @Results(id = "citiesResults", value = {
            @Result(column = "country_id", property = "countryId"),//防止countryId没有值
            @Result(column = "country_id", property = "country",//第一个参数是需要查询的条件，第二个参数是pojo类对应多一方的参数
                    javaType = Country.class,//结果类型
                    one = @One(select = "com.sfac.java_spring_boot.models.test.dao.CountryDao.getCountryByCountryId2"))
    })
    List<City> getCitiesByCountryId(int countryId);


    /*脚本查询------  模糊查询，排序字段，以及排序规则*/
    @Select("<script>"+
        "select * from m_city "
       +"<where>"
       +"<if test='keyWord != \"\" and keyWord != null'>"
       +"and (city_name like '%${keyWord}%' or local_city_name like '%${keyWord}%')"
       +"</if>"
       +"</where>"
       +"<choose>"
       +"<when test='orderBy!= \"\" and orderBy != null'>"
       +" order by ${orderBy} ${sort}"
       +"</when>"
       +"<otherwise>"
       +" order by city_id desc"
       +"</otherwise>"
       +"</choose>"
       +"</script>"
    )
    List<City> getCitiesBySearchVo(SearchVo searchVo);


    /*插入记录*/
    @Insert("insert into m_city(city_name,local_city_name,country_id,date_created) values(#{cityName},#{localCityName},#{countryId},#{dateCreated})")
    @Options(useGeneratedKeys = true,keyColumn = "city_name",keyProperty = "cityId")//键值映射
    void insertCity(City city);


    /*修改记录*/
    @Update("update m_city set city_name = #{cityName} where city_id = #{cityId}")
    void updateCity(City city);


    /*删除记录*/
    @Update("delete from m_city where city_id = #{cityId}")
    void deleteCity(City city);
}













