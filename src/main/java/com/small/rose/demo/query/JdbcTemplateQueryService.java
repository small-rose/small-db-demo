package com.small.rose.demo.query;

import com.small.rose.demo.utils.CamelCaseUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ JdbcTemplateQueryService ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/7/4 12:08
 * @Version ： 1.0
 **/

@Service
public class JdbcTemplateQueryService {

    @Autowired
    private JdbcTemplate jdbcTemplate ;



    public <T> List<T> queryList(String sql, List params, int size, Class clazz){
        final List<T> result = new ArrayList<>();
        jdbcTemplate.setFetchSize(size);
        jdbcTemplate.query(sql,rs -> {

            try {
                // 字段名称
                List<String> columnNames = new ArrayList<>();
                ResultSetMetaData meta = rs.getMetaData();
                int num = meta.getColumnCount();
                for (int i = 0; i < num; i++) {
                    columnNames.add(meta.getColumnLabel(i + 1));
                }
                // 设置值
                do {
                    T obj = (T) clazz.getConstructor().newInstance();
                    for (int i = 0; i < num; i++) {
                        // 获取值
                        Object value = rs.getObject(i + 1);
                        // table.column形式的字段去掉前缀table.
                        String columnName = resolveColumn(columnNames.get(i));
                        // 下划线转驼峰
                        String property = CamelCaseUtils.toCamelCase(columnName);
                        // 复制值到属性，这是spring的工具类
                        BeanUtils.copyProperty(obj, property, value);
                    }
                    //no.set(no.get() + 1);
                    //BeanUtils.copyProperty(obj, "no", no.get());
                    result.add(obj);


                } while (rs.next());
            } catch (Exception e) {
                e.printStackTrace();
                throw new QueryException("查询报错了");
            }
        }, params);

        return result;
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
