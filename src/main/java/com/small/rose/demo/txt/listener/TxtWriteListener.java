package com.small.rose.demo.txt.listener;

import com.small.rose.demo.txt.metadata.FileWriterContext;

/**
 * @Project : easy-txt
 * @Author : small-rose
 * @Description : [ ReadListener ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/5 10:56
 * @Version ： 1.0
 **/
public interface TxtWriteListener<T> {


    /**
     *
     * @param object
     * @return
     */
    boolean invokeLine(T object);

    /**
     *
     * @param object
     */
    void doAfterWrite(Object object);

    /**
     *
     * @param exception
     * @param context
     * @throws Exception
     */
    void onException(Exception exception, FileWriterContext context) throws Exception ;
}
