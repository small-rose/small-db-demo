package com.small.rose.demo.module.hibernate.service;

import com.small.rose.demo.module.hibernate.dao.TorderRepository;
import com.small.rose.demo.module.hibernate.entities.Torder;
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
public class TorderService {

    @Autowired
    private TorderRepository torderRepository ;

    public Torder add(Torder torder){
        Torder torder1 = torderRepository.save(torder);
        return  torder1 ;
    }


    public List<Torder> queryList(){
        return  torderRepository.findAll();
    }


    public Torder queryById(Long id){
        Optional<Torder> byId = torderRepository.findById(id);
        return  byId.get();
    }

    public List<Torder> queryByStauts(String status){
        List<Torder> torder = torderRepository.queryByStauts(status);
        return  torder;
    }
}
