package com.small.rose.demo.txt.listener;

import com.small.rose.demo.txt.metadata.FileWriterContext;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ DefaultTxtWriteListener ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/7/12 17:48
 * @Version ： 1.0
 **/
public abstract class DefaultTxtWriteListener<T> implements TxtWriteListener<T> {


    @Override
    public boolean invokeLine(T data){
        return true;
    }


    @Override
    public void onException(Exception exception, FileWriterContext context) throws Exception {
        throw exception ;
    }
}
