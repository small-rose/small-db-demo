package com.small.rose.demo.hibernate;

import com.small.rose.demo.DbDemoApplicationTests;
import com.small.rose.demo.module.hibernate.entities.Torder;
import com.small.rose.demo.module.hibernate.service.interfaces.JpaTransactionalServiceI;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ JpaTransactionalTest ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/7/3 22:29
 * @Version ： 1.0
 **/

@Slf4j
public class JpaTransactionalTest extends DbDemoApplicationTests {

    @Autowired
    JpaTransactionalServiceI jpaTransactionalServiceI ;


    @Test
    public void  jpaTest() {
        List<Torder> torderList = new ArrayList<>();
        Torder torder = new Torder();
        torder.setCreatetime(new Date());
        torder.setOrderNo("D0003");
        torder.setOrderName("S0001");
        torder.setOrderStatus("01");
        torder.setOrderDesc("订单D0003");
        torder.setOrderDate(new Date());
        torderList.add(torder);

        Torder torder2 = new Torder();
        torder2.setCreatetime(new Date());
        torder2.setOrderNo("D0004");
        torder2.setOrderName("S0002");
        torder2.setOrderDesc("订单D0004");
        torder2.setOrderStatus("01");
        torder2.setOrderDate(new Date());

        torderList.add(torder2);

        jpaTransactionalServiceI.payOrder(torderList);

    }

}
