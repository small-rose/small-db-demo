package com.small.rose.demo.module.hibernate.service;

import com.small.rose.demo.module.hibernate.dao.TinventoryRepository;
import com.small.rose.demo.module.hibernate.entities.Tinventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ TorderService ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/6/19 16:46
 * @Version ： 1.0
 **/

@Service
public class TinventoryService {

    @Autowired
    private TinventoryRepository tinventoryRepository ;

    public Tinventory save(Tinventory tinventory){
        Tinventory torder1 = tinventoryRepository.save(tinventory);
        return  torder1 ;
    }


    public List<Tinventory> queryList(){
        return  tinventoryRepository.findAll();
    }


    public Tinventory queryByShopNo(String shopNo){
        Optional<Tinventory> byId = tinventoryRepository.queryByShopNo(shopNo);
        return  byId.get();
    }


}
