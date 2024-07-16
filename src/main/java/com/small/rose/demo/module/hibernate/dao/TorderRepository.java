package com.small.rose.demo.module.hibernate.dao;

import com.small.rose.demo.module.hibernate.entities.Torder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ TorderRepository ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/6/19 16:39
 * @Version ： 1.0
 **/
@Repository
public interface TorderRepository extends JpaRepository<Torder,Long> {


    @Query("select t from Torder t WHERE t.orderStatus = ?1")
    List<Torder> queryByStauts(String id);
}
