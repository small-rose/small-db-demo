package com.small.rose.demo.mybatis;

import com.small.rose.demo.DbDemoApplicationTests;
import com.small.rose.demo.module.mybatis.entity.Tuser;
import com.small.rose.demo.module.mybatis.service.MybatisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ HibernateTest ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/6/19 16:52
 * @Version ： 1.0
 **/

@Slf4j
public class MybatisTest extends DbDemoApplicationTests {

    @Autowired
    MybatisService mybatisService ;
    @Test
    public void queryTest() {
        List<Tuser> list = mybatisService.queryList();
        list.forEach(t->{
            log.info("Tuser : " +t);
        });
        log.info("total " + list.size());
    }

    @Test
    public void queryByIdTest() {
        Tuser tuser = mybatisService.queryById(1L);
        log.info("tuser " + tuser);
    }


}
