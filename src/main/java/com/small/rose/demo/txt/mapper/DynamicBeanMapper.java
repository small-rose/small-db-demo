package com.small.rose.demo.txt.mapper;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ DynamicBeanMapper ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/7/12 15:13
 * @Version ： 1.0
 **/
public class DynamicBeanMapper <T> implements RowMapper<T> {
    private Class<T> beanClass;

    public DynamicBeanMapper(Class<T> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        T bean;
        try {
            bean = beanClass.newInstance();
            // 遍历查询结果的列，并动态设置属性值
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                String columnName = rs.getMetaData().getColumnName(i);
                Object value = rs.getObject(i);
                // 根据 columnName 动态设置 bean 的属性值
                setProperty(bean, columnName, value);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new SQLException("Failed to create an instance of the bean class.", e);
        }


        return bean;
    }

    private void setProperty(T bean, String propertyName, Object value) throws InvocationTargetException, IllegalAccessException {
        // 动态设置属性值的逻辑
        // 这里可以使用反射或其他方式来设置属性值
        // 例如：使用 Apache BeanUtils 设置属性值
        BeanUtils.setProperty(bean, propertyName, value);
    }

}
