package com.small.rose.demo.txt;


import com.small.rose.demo.txt.function.QueryPageList;
import com.small.rose.demo.txt.listener.PageWriteSupplierListener;
import com.small.rose.demo.txt.listener.TxtSupplierListener;
import com.small.rose.demo.txt.listener.TxtWriteListener;
import com.sun.istack.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.util.List;

/**
 * @Project : easy-txt
 * @Author : small-rose
 * @Description : [ EasyTxt ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/5 15:43
 * @Version ： 1.0
 **/
public class EasyTxtFactory {


    public static <T> TxtFileQueryWriter write(@NotNull File file, @NotNull String splitor, @NotNull JdbcTemplate jdbcTemplate) {
        return write(file, splitor, jdbcTemplate, null, 1000, -1);
    }

    public static <T> TxtFileQueryWriter write(@NotNull File file, @NotNull String splitor, @NotNull JdbcTemplate jdbcTemplate, TxtWriteListener writeListener) {
        return write(file, splitor, jdbcTemplate, writeListener, 1000, -1);
    }

    public static <T> TxtFileQueryWriter write(@NotNull File file, @NotNull String splitor, @NotNull JdbcTemplate jdbcTemplate, int fetchSize, int fileRecordSize) {
        return write(file, splitor, jdbcTemplate, null, fetchSize, fileRecordSize);
    }

    public static <T> TxtFileQueryWriter write(@NotNull File file, @NotNull String splitor, @NotNull JdbcTemplate jdbcTemplate, int fetchSize, int fileRecordSize, TxtWriteListener writeListener) {
        return write(file, splitor, jdbcTemplate, writeListener, fetchSize, fileRecordSize);
    }
    /**
     * @param file
     * @param splitor
     * @param jdbcTemplate
     * @param writeListener  计算结果
     * @param fetchSize
     * @param fileRecordSize
     * @param <T>
     * @return
     */
    public static <T> TxtFileQueryWriter write(File file, String splitor, JdbcTemplate jdbcTemplate, TxtWriteListener writeListener, int fetchSize, int fileRecordSize) {
        TxtFileQueryWriter txtFileQueryWriter = new TxtFileQueryWriter();
        txtFileQueryWriter.file(file);
        if (splitor != null) {
            txtFileQueryWriter.split(splitor);
        }
        if (jdbcTemplate != null) {
            txtFileQueryWriter.setJdbcTemplate(jdbcTemplate);
        }
        txtFileQueryWriter.setFetchSize(fetchSize);
        txtFileQueryWriter.setFileRecordSize(fileRecordSize);

        if (writeListener != null) {
            txtFileQueryWriter.registerTxtWriteListener(writeListener);
        }
        return txtFileQueryWriter;
    }

    public static <T> TxtFileWriter write(@NotNull File file, @NotNull Class<T> bean, @NotNull String splitor, @NotNull TxtSupplierListener txtSupplierListener) {
        return write(file, bean, splitor, txtSupplierListener, null);
    }



    public static <T> TxtFileWriter write(@NotNull File file, @NotNull Class<T> bean, @NotNull String splitor, @NotNull TxtSupplierListener txtSupplierListener, TxtWriteListener writeListener) {
        TxtFileWriter txtFileWriter = new TxtFileWriter();
        txtFileWriter.file(file);
        if (bean != null) {
            txtFileWriter.bean(bean);
        }
        if (splitor != null) {
            txtFileWriter.split(splitor);
        }
        if (txtSupplierListener != null) {
            txtFileWriter.registerSupplierListener(txtSupplierListener);
        }
        if (writeListener != null) {
            txtFileWriter.registerTxtWriteListener(writeListener);
        }
        return txtFileWriter;
    }


    @NotNull
    public static <T> TxtFileWriter write(@NotNull File file, @NotNull Class<T> bean, @NotNull String splitor, @NotNull int pageNo, @NotNull int pageSize, @NotNull QueryPageList<List<T>> query) {
        TxtFileWriter txtFileWriter = new TxtFileWriter();
        txtFileWriter.file(file);
        if (bean != null) {
            txtFileWriter.bean(bean);
        }
        if (splitor != null) {
            txtFileWriter.split(splitor);
        }
        if (query != null) {
            txtFileWriter.registerSupplierListener(new PageWriteSupplierListener(pageNo, pageSize, query));
        }
        return txtFileWriter;
    }


}
