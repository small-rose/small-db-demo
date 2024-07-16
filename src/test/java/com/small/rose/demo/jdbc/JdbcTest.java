package com.small.rose.demo.jdbc;

import com.small.rose.demo.DbDemoApplicationTests;
import com.small.rose.demo.module.hibernate.entities.Torder;
import com.small.rose.demo.module.jdbc.JdbcQueryService;
import com.small.rose.demo.module.mybatis.entity.Tuser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ JdbcTest ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/6/19 17:57
 * @Version ： 1.0
 **/

@Slf4j
public class JdbcTest extends DbDemoApplicationTests {


    @Autowired
    JdbcQueryService jdbcQueryService ;
    @Test
    public void queryTest() {
        List<Object> param = new ArrayList<>();
        param.add("01");

        List<Torder> list = jdbcQueryService.queryListByStatus(param);
        list.forEach(t->{
            log.info("Torder : " +t);
        });
        log.info("total " + list.size());
    }

    @Test
    public void queryByIdTest() {
        Tuser tuser = jdbcQueryService.queryById(1L);
        log.info("tuser " + tuser);
    }
}
