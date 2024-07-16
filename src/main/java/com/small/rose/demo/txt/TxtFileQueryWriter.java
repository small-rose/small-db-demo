package com.small.rose.demo.txt;


import com.small.rose.demo.txt.executor.DefaultFileWriteExecutor;
import com.small.rose.demo.txt.listener.TxtWriteListener;
import com.small.rose.demo.txt.metadata.AbstractFileWriter;
import com.small.rose.demo.utils.CamelCaseUtils;
import com.small.rose.demo.utils.MemoryUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.QueryException;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Project : easy-txt
 * @Author : small-rose
 * @Description : [ TxtFileReader ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/5 10:56
 * @Version ： 1.0
 **/
@Slf4j
public class TxtFileQueryWriter extends AbstractFileWriter {

    private DefaultFileWriteExecutor fileWriteExecutor;

    public <T> void doWrite(String sql, Class<T> object, Object... params) {
        fileWriteExecutor = new DefaultFileWriteExecutor(this);
        prepare(object);

        List<TxtWriteListener> writeListenerList = getWriteListenerList();

        final List<T> result = new ArrayList<>();

        String countsql = COUNT_SQL.replace("${SQL}", sql);
        jdbcTemplate.setQueryTimeout(-1);
        Integer aLong = jdbcTemplate.queryForObject(countsql, Integer.class);
        if (ObjectUtils.isEmpty(aLong) || aLong<=0 ){
            log.warn("count sql query result is 0 , no file will be written , do nothing !");
            return;
        }
        setCountSize(aLong);

        // 查到数据可以写
        List<File> fileList = fileWriteExecutor.getFileNameList();
        AtomicInteger pageNum = new AtomicInteger(0);
        AtomicInteger totalWritten = new AtomicInteger(0);
        AtomicReference<File> currentFile = new AtomicReference(0);


        getJdbcTemplate().setFetchSize(getFetchSize());
        getJdbcTemplate().query(sql, rs -> {
            try {
                if (totalWritten.get()==0){
                    currentFile.set(fileList.get(pageNum.get()));
                    log.info("current file : {} ， path : {}", (pageNum.get()+1), currentFile.get().getAbsolutePath());
                }
                // 字段名称
                List<String> columnNames = new ArrayList<>();
                ResultSetMetaData meta = rs.getMetaData();
                int num = meta.getColumnCount();
                String cname = null;
                String property = null ;
                for (int i = 0; i < num; i++) {
                    // table.column形式的字段去掉前缀table.
                    cname = resolveColumn(meta.getColumnLabel(i + 1 ));
                    // 下划线转驼峰
                    property = CamelCaseUtils.toCamelCase(cname);
                    columnNames.add(property);
                }
                // 设置值
                HashMap<String, Object> row = null;
                Object value = null ;
                String columnName = null;
                while (rs.next()) {
                    row = new HashMap();
                    T obj = (T) object.getConstructor().newInstance();
                    for (int i = 0; i < num; i++) {
                        // 获取值
                        value = rs.getObject(i + 1);
                        // table.column形式的字段去掉前缀table.
                        columnName = columnNames.get(i);
                        //BeanUtils.copyProperty(obj, property, value);
                        row.put(columnName, value);
                    }
                    BeanUtils.populate(obj, row);
                    // 过滤自定义规则数据
                    if (writeListenerList.size()>0 && !writeListenerList.get(0).invokeLine(obj)){
                        continue;
                    }
                    result.add(obj);


                    if (result.size() >= BATCH_WRITE_SIZE) {
                        fileWriteExecutor.execute(currentFile.get(), result);
                        totalWritten.addAndGet(result.size());
                        result.clear();
                        log.info(" bath writting : {} file {}");
                        long totalMemory = MemoryUtils.getTotalMemory(result);

                    }

                    if (getFileRecordSize()!=-1 && result.size() >= (getFileRecordSize()-totalWritten.get())){
                        fileWriteExecutor.execute(currentFile.get(), result);
                        totalWritten.addAndGet(result.size());
                        result.clear();
                        log.info(" bath writting end : {} file {}");
                    }
                }

                if (result.size() > 0) {
                    // 尾部数据
                    fileWriteExecutor.execute(result);
                }
                if (getFileRecordSize()==-1 || result.size()>0){

                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new QueryException("查询报错了");
            }finally {
                if (writeListenerList.size()>0 ) {
                    // 写文件结束
                    writeListenerList.get(0).doAfterWrite(fileWriteExecutor.getFileWriterContext());
                }
            }
        }, params);
    }



    private String resolveColumn(String column) {
        final int notExistIndex = -1;
        int index = column.indexOf(".");
        if (index == notExistIndex) {
            return column;
        }
        return column.substring(index + 1);
    }



}
