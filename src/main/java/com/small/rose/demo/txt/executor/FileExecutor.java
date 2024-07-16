package com.small.rose.demo.txt.executor;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Project : easy-txt
 * @Author : small-rose
 * @Description : [ FileReadExecutor ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/8 11:03
 * @Version ： 1.0
 **/
public interface FileExecutor {

    void execute()  throws IOException;



    <T> void execute(File file, List<T> data) throws IOException;
}
