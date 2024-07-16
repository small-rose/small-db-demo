package com.small.rose.demo.hibernate;

import com.small.rose.demo.DbDemoApplicationTests;
import com.small.rose.demo.module.hibernate.entities.Torder;
import com.small.rose.demo.module.hibernate.service.TorderService;
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
public class HibernateTest extends DbDemoApplicationTests {

    @Autowired
    TorderService torderService ;
    @Test
    public void queryTest() {
        List<Torder> list = torderService.queryList();
        list.forEach(t->{
            log.info("Torder : " +t);
        });
        log.info("total " + list.size());
    }

    @Test
    public void queryByIdTest() {
        Torder torder = torderService.queryById(1L);
        log.info("torder " + torder);
    }

    @Test
    public void queryByStautsTest() {
        List<Torder> torders = torderService.queryByStauts("01");
        log.info("status list total " + torders.size());
    }
}
