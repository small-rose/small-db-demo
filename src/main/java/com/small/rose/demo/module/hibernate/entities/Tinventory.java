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
@Table(name = "T_INVENTORY")
public class Tinventory {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
    @SequenceGenerator(name = "SEQUENCE", sequenceName = "SEQ_INVENTORY", allocationSize = 1)
    private Long id ;

    @Column(name = "SHOP_NO")
    private String shopNo ;

    @Column(name = "SHOP_NAME")
    private String shopName ;

    @Column(name = "SHOP_NUMBER")
    private Long shopNumber ;

    @Column(name = "SHOP_STATUS")
    private String shopStatus ;

    @Column(name = "CREATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createtime ;

    @Column(name = "LASTOPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastopdate ;


    @Override
    public String toString() {
        return "Tinventory{" +
                "id=" + id +
                ", shopNo='" + shopNo + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopNumber=" + shopNumber +
                ", shopStatus='" + shopStatus + '\'' +
                ", createtime=" + createtime +
                ", lastopdate=" + lastopdate +
                '}';
    }
}
