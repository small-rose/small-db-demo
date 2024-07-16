package com.small.rose.demo.txt.listener;

import java.util.List;

/**
 * @Project : easy-txt
 * @Author : small-rose
 * @Description : [ ReadListener ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/5 10:56
 * @Version ： 1.0
 **/
public interface TxtSupplierListener<T> {


    /**
     * 写行
     */
    List<T> queryData();


}
