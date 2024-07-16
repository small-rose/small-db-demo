package com.small.rose.demo.txt.exception;

/**
 * @Project : easy-txt
 * @Author : small-rose
 * @Description : [ FiledIndexException ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/9 11:47
 * @Version ： 1.0
 **/
public class FileRandomException extends RuntimeException {
    public FileRandomException(String message) {
        super(message);
    }

    public FileRandomException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
