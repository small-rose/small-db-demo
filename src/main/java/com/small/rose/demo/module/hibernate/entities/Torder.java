package com.small.rose.demo.module.hibernate.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ Torder ] 说明： 订单类， hibernate模式
 * @Function :  功能说明：无
 * @Date ：2024/6/19 16:22
 * @Version ： 1.0
 **/

@Data
@Entity
@Table(name = "T_ORDER")
public class Torder {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
    @SequenceGenerator(name = "SEQUENCE", sequenceName = "SEQ_TEST_01", allocationSize = 1)
    private Long id ;

    @Column(name = "ORDER_NO")
    private String orderNo ;

    @Column(name = "ORDER_NAME")
    private String orderName ;

    @Column(name = "ORDER_DESC")
    private String orderDesc ;

    @Column(name = "ORDER_STATUS")
    private String orderStatus ;

    @Column(name = "ORDER_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate ;

    @Column(name = "CREATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createtime ;


    @Override
    public String toString() {
        return "Torder{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderDesc='" + orderDesc + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderDate=" + orderDate +
                ", createtime=" + createtime +
                '}';
    }
}
