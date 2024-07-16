package com.small.rose.demo.module.hibernate.service.impl;

import com.small.rose.demo.module.hibernate.entities.Tinventory;
import com.small.rose.demo.module.hibernate.entities.Torder;
import com.small.rose.demo.module.hibernate.service.TinventoryService;
import com.small.rose.demo.module.hibernate.service.TorderService;
import com.small.rose.demo.module.hibernate.service.interfaces.JpaTransactionalServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ JpaTransactionalServiceImpl ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/7/3 21:49
 * @Version ： 1.0
 **/

@Service
@Transactional
public class JpaTransactionalServiceImpl implements JpaTransactionalServiceI {

    @Autowired
    private TorderService torderService ;
    @Autowired
    private TinventoryService tinventoryService ;


    @Override
    public void payOrder(List<Torder> torderList) {

        for (Torder torder :torderList) {

            torderService.add(torder);
            Tinventory tinventory = tinventoryService.queryByShopNo(torder.getOrderName());
            tinventory.setShopNumber(tinventory.getShopNumber()-1);
            tinventoryService.save(tinventory);

        }
        throw new RuntimeException("WTF");
    }
}
