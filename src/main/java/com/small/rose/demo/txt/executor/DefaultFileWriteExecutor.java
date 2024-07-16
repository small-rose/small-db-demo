package com.small.rose.demo.txt.executor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.file.FileWriter;
import com.small.rose.demo.txt.annotation.format.DateFormatFiled;
import com.small.rose.demo.txt.annotation.format.NumberFormatFiled;
import com.small.rose.demo.txt.converter.ConvertData;
import com.small.rose.demo.txt.converter.factory.ConverterFactory;
import com.small.rose.demo.txt.converter.strategy.Converter;
import com.small.rose.demo.txt.listener.PageWriteSupplierListener;
import com.small.rose.demo.txt.listener.TxtSupplierListener;
import com.small.rose.demo.txt.metadata.FileWriterContext;
import com.small.rose.demo.utils.FileNameUtil;
import com.small.rose.demo.utils.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Project : easy-txt
 * @Author : small-rose
 * @Description : [ DefaultFileReadExecutor ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/8 11:14
 * @Version ： 1.0
 **/

public class DefaultFileWriteExecutor implements FileExecutor {

    private FileWriterContext fileWriterContext;

    public FileWriterContext getFileWriterContext() {
        return fileWriterContext;
    }

    public DefaultFileWriteExecutor(FileWriterContext fileWriterContext){
        this.fileWriterContext = fileWriterContext ;
    }

    /**
     * 由 supplierListener 负责查
     * 然后再写
     */
    @Override
    public void execute() {
        File file = fileWriterContext.getFile();
        String splitor = fileWriterContext.getSplitor();

        List<TxtSupplierListener> txtSupplierListenerList = fileWriterContext.getSupplierListenerList();
        FileWriter fileWriter = new FileWriter(file);
        //System.out.println(readListenerList);
        // 调用函数式接口获取查询结果
        PageWriteSupplierListener supplierListener = (PageWriteSupplierListener) txtSupplierListenerList.get(0);
        List<Object> list = supplierListener.queryData();

        if (!CollectionUtil.isEmpty(list)) {
            final List<String> data = new ArrayList<>(list.size());
            list.stream().forEach(o -> {

                // 转成文件行
                String line = beanToLine(o, splitor);
                data.add(line);
            });
            //System.out.println("data = " + data);
            fileWriter.appendLines(data);
        }
    }



    /**
     * 只管写，不管查
     * @param <T>
     * @param data
     */
    @Override
    public <T> void execute(File file, List<T> data) {
        String splitor = fileWriterContext.getSplitor();
        FileWriter fileWriter = new FileWriter(file);
        if (!CollectionUtil.isEmpty(data)) {
            final List<String> dataTemp = new ArrayList<>(data.size());
            data.stream().forEach(o -> {
                String line = beanToLine(o, splitor);
                dataTemp.add(line);
            });
            fileWriter.appendLines(dataTemp);
        }
    }


    public List<File> getFileNameList() {
        List<File> fileList = new ArrayList<>();



        return fileList ;
    }

    private String beanToLine(Object o, String splitor) {
        Map<Integer, Field> beanFieldMap = fileWriterContext.getBeanFieldMap();
        Set<Integer> integers = beanFieldMap.keySet();

        StringBuffer line = new StringBuffer("") ;
        for (Integer integer : integers) {
            Field field = beanFieldMap.get(integer);
            field.setAccessible(true);
            String columnVal =  null;
            Class<?> type = field.getType();
            //System.out.println(type.getName());

            Converter converter = ConverterFactory.getConverterByType(type);
            ConvertData convertData = getConvertData(field);
            try {
                convertData.setSource(field.get(o));

                columnVal = converter.convertToString(convertData);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            //columnVal = (String) field.get(o);
            line = line.append(columnVal).append(splitor);
        }

        return line.toString().substring(0, line.length()-1);
    }


    private ConvertData getConvertData(Field field){
        ConvertData convertData = new ConvertData();
        DateFormatFiled dateFormatFiled = field.getAnnotation(DateFormatFiled.class);
        NumberFormatFiled numberFormatFiled = field.getAnnotation(NumberFormatFiled.class);
        if (dateFormatFiled !=null && StringUtils.isNotBlank(dateFormatFiled.value())) {
            convertData.setDateFormat(dateFormatFiled.value());
        }

        if (numberFormatFiled!=null && StringUtils.isNotBlank(numberFormatFiled.value()) ){
            convertData.setNumberFormat(numberFormatFiled.value());
        }
        return  convertData;
    }
}
