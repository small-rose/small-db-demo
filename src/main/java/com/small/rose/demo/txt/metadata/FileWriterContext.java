package com.small.rose.demo.txt.metadata;

import com.small.rose.demo.txt.listener.TxtSupplierListener;
import com.small.rose.demo.txt.listener.TxtWriteListener;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Project : easy-txt
 * @Author : small-rose
 * @Description : [ FileReaderContext ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/5 15:46
 * @Version ： 1.0
 **/
public abstract class FileWriterContext {
    protected static final String COUNT_SQL = "select count(*) from ( ${SQL} )" ;
    protected static final Integer BATCH_WRITE_SIZE = 1000 ; ;

    protected File file  ;
    protected String splitor ;
    protected Class object ;
    protected Object objectInstance ;
    protected boolean isToBean ;
    protected List<TxtSupplierListener> supplierListenerList ;
    protected List<TxtWriteListener> writeListenerList ;
    protected Map<Integer, Field> beanFieldMap ;

    protected String sql ;
    protected int countSize ;
    protected int fetchSize ;
    protected JdbcTemplate jdbcTemplate ;
    protected List params ;
    protected int fileRecordSize ;
    protected List<File> fileList ;
    protected String fileNameReg ;


    FileWriterContext(){
        this.supplierListenerList = new ArrayList<>();
        this.writeListenerList = new ArrayList<>();
    }

    public void registerSupplierListener(TxtSupplierListener writeListener) {
        supplierListenerList.add(writeListener);
    }

    public void registerTxtWriteListener(TxtWriteListener writeListener) {
        writeListenerList.add(writeListener);
    }

    public abstract void file(File file);

    public abstract void sql(String sql);

    public abstract String getSql();

    public abstract JdbcTemplate getJdbcTemplate();

    public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

    public abstract List getParams();

    public abstract void setParams(List params);


    public abstract int getFetchSize();

    public abstract void setFetchSize(int fetchSize) ;

    public abstract int getFileRecordSize();

    public abstract void setFileRecordSize(int fileRecordSize);

    public abstract File getFile();

    public abstract void split(String splitor);

    public abstract String getSplitor();

    public abstract void bean(Class head);

    public abstract Object getBean();

    public abstract List<TxtSupplierListener> getSupplierListenerList();

    public abstract List<TxtWriteListener> getWriteListenerList();

    public abstract Map<Integer, Field> getBeanFieldMap();


    public abstract boolean isToBean();

    public abstract int getCountSize();

    public abstract void setCountSize(int countSize);

    public abstract List<File> getFileList();

    public abstract void setFileList(List<File> fileList);

    public abstract String getFileNameReg();

    public abstract void setFileNameReg(String fileNameReg);
}
