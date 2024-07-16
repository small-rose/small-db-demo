package com.small.rose.demo.utils;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ MemoryUtils ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/7/12 18:06
 * @Version ： 1.0
 **/

@Component
public class MemoryUtils {

    @PostConstruct
    public void  init(){
        System.setProperty("java.vm.name","Java HotSpot(TM) ");
    }
    /**
     * JDK自带内存计算工具
     * */
    public static long getTotalMemory(Object obj) {
        //System.setProperty("java.vm.name","Java HotSpot(TM) ");
        long totalMemory = ObjectSizeCalculator.getObjectSize(obj);
        //System.out.println("getTotalMemory getObject memory : " + totalMemory);
        return totalMemory;
    }

}
