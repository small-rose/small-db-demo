package com.small.rose.demo.module.hibernate.service.interfaces;

import com.small.rose.demo.module.hibernate.entities.Torder;

import java.util.List;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ JpaTransactionalServiceI ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/7/3 21:49
 * @Version ： 1.0
 **/
public interface JpaTransactionalServiceI {

    /**
     * 下单，扣库存
     */
    public void payOrder(List<Torder> torderList);
}
