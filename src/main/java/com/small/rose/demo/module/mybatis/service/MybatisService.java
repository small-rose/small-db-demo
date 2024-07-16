package com.small.rose.demo.module.mybatis.service;

import com.small.rose.demo.module.mybatis.entity.Tuser;
import com.small.rose.demo.module.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ MybatisService ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/6/18 17:49
 * @Version ： 1.0
 **/

@Service
public class MybatisService {

    @Autowired
    private UserMapper userMapper ;

    public List<Tuser> queryList(){
        return  userMapper.selectAll();
    }

    public Tuser queryById(Long id){
        return  userMapper.selectByPrimaryKey(id);
    }
}
