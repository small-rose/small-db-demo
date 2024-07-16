package com.small.rose.demo.module.mybatis.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ Tuser ] 说明：订单类， mybatis 模式
 * @Function :  功能说明：无
 * @Date ：2024/6/19 16:20
 * @Version ： 1.0
 **/

@Data
public class Tuser {

    private Long id ;
    private String userCode ;
    private String userName ;
    private Date briDate ;
    private String descmark ;
    private Date createtime ;


    @Override
    public String toString() {
        return "Tuser{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", briDate=" + briDate +
                ", descmark='" + descmark + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}
