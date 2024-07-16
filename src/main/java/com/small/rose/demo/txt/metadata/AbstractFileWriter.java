package com.small.rose.demo.txt.metadata;

import cn.hutool.core.lang.Assert;
import com.small.rose.demo.txt.annotation.TxtFiled;
import com.small.rose.demo.txt.converter.ConverterHelper;
import com.small.rose.demo.txt.exception.FiledIndexException;
import com.small.rose.demo.txt.listener.TxtSupplierListener;
import com.small.rose.demo.txt.listener.TxtWriteListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Project : easy-txt
 * @Author : small-rose
 * @Description : [ AbstractFileReader ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/9 14:18
 * @Version ： 1.0
 **/
public abstract class AbstractFileWriter extends  FileWriterContext {

    @Override
    public void file(File file) {
        this.file = file;
    }

    @Override
    public void split(String splitor) {
        if (object!=null) {
            Assert.isTrue(splitor != null, "设置Class参数必须有行分隔符");
        }
        this.splitor = splitor;
    }

    @Override
    public File getFile() {
        return file ;
    }

    @Override
    public String getSplitor() {
        return splitor;
    }

    @Override
    public void bean(Class bean) {
        this.object = bean ;
    }


    @Override
    public Object getBean() {
        return object;
    }

    @Override
    public List<TxtSupplierListener> getSupplierListenerList() {
        return supplierListenerList;
    }
    @Override
    public List<TxtWriteListener> getWriteListenerList() {
        return writeListenerList;
    }
    @Override
    public Map<Integer, Field> getBeanFieldMap() {
        return beanFieldMap;
    }

    @Override
    public boolean isToBean() {
        return isToBean;
    }

    @Override
    public void sql(String sql) {
        this.sql = sql ;
    }

    @Override
    public String getSql() {
        return this.sql;
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate ;
    }

    @Override
    public List getParams() {
        return this.params;
    }

    @Override
    public void setParams(List params) {
        this.params = params ;
    }

    @Override
    public int getFetchSize() {
        return this.fetchSize;
    }

    @Override
    public void setFetchSize(int fetchSize) {
        this.fetchSize= fetchSize ;
    }

    @Override
    public int getFileRecordSize() {
        return fileRecordSize;
    }

    @Override
    public void setFileRecordSize(int fileRecordSize) {
        this.fileRecordSize=fileRecordSize;
    }

    @Override
    public int getCountSize() {
        return countSize;
    }

    @Override
    public void setCountSize(int countSize) {
        this.countSize = countSize ;
    }

    @Override
    public List<File> getFileList() {
        return fileList;
    }

    @Override
    public void setFileList(List<File> fileList) {

    }
    @Override
    public String getFileNameReg() {
        return fileNameReg;
    }

    @Override
    public void setFileNameReg(String fileNameReg) {
        this.fileNameReg = fileNameReg;
    }

    protected void prepare(Class obj) {

        if (obj!=null){
            bean(obj);
        }
        if (object != null && object instanceof Class && StringUtils.hasText(splitor)) {
            this.isToBean = true;
            initBean(object);
        }
        ConverterHelper.initConverters(beanFieldMap);
    }


    protected void initBean(Class object) {
        try {

            objectInstance = object.newInstance();
            Field[] fields = objectInstance.getClass().getDeclaredFields();
            beanFieldMap = new TreeMap<Integer, Field>();
            for (Field field : fields){
                TxtFiled annotation = field.getAnnotation(TxtFiled.class);
                if (beanFieldMap.keySet().contains(annotation.index())){
                    throw new FiledIndexException("Duplicate indexes were found, please ensure that the index property values of the TxtFiled annotations in the bean ["+object.getName()+"] are unique.");
                }
                beanFieldMap.put(annotation.index(), field);
            }
            //System.out.println(beanFieldMap);
            if (beanFieldMap.size()<=0){
                throw new FiledIndexException("Not found "+object.getTypeName()+" TxtFiled annotation ! ");
            }
        } catch (InstantiationException | IllegalAccessException e) {
            //e.printStackTrace();
            throw new FiledIndexException("init bean field index error ", e);
        }
    }


}
