package com.small.rose.demo.vo;

import com.small.rose.demo.txt.annotation.TxtFiled;
import com.small.rose.demo.txt.annotation.TxtPorperty;

import java.util.Date;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ MyListener ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/7/4 13:15
 * @Version ： 1.0
 **/

@TxtPorperty
public class TorderVO {

    @TxtFiled( index = 0 )
    private String line1;

    @TxtFiled( index = 1 )
    private String line2;

    @TxtFiled( index = 2 )
    private String line3;

    @TxtFiled( index = 3 )
    private Date line4;

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public Date getLine4() {
        return line4;
    }

    public void setLine4(Date line4) {
        this.line4 = line4;
    }
}
