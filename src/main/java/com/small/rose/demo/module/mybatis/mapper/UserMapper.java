package com.small.rose.demo.module.mybatis.mapper;

import com.small.rose.demo.module.mybatis.entity.Tuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ UserMapper ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/6/19 16:20
 * @Version ： 1.0
 **/


public interface UserMapper {


    Tuser selectByPrimaryKey(@Param("id") Long id);


    List<Tuser> selectAll();


    int updateByPrimaryKey(@Param("record") Tuser record);
}
