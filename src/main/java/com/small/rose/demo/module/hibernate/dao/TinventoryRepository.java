package com.small.rose.demo.module.hibernate.dao;

import com.small.rose.demo.module.hibernate.entities.Tinventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ TinventoryRepository ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/7/3 21:55
 * @Version ： 1.0
 **/
public interface TinventoryRepository extends JpaRepository<Tinventory,Long> {

    @Query("select t from Tinventory t WHERE t.shopNo = ?1")
    Optional<Tinventory> queryByShopNo(String shopNo);
}
