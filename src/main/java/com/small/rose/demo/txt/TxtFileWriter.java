package com.small.rose.demo.txt;


import com.small.rose.demo.txt.executor.DefaultFileWriteExecutor;
import com.small.rose.demo.txt.metadata.AbstractFileWriter;

/**
 * @Project : easy-txt
 * @Author : small-rose
 * @Description : [ TxtFileReader ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/5 10:56
 * @Version ： 1.0
 **/
public class TxtFileWriter extends AbstractFileWriter {

    private DefaultFileWriteExecutor fileWriteExecutor;


    public void doWrite() {
        fileWriteExecutor = new DefaultFileWriteExecutor(this);
        prepare(null);

        fileWriteExecutor.execute();

    }


}
