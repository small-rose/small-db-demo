package com.small.rose.demo.txt.converter.strategy.impl;


import com.small.rose.demo.txt.converter.ConvertData;
import com.small.rose.demo.txt.converter.strategy.Converter;
import com.small.rose.demo.utils.NumberUtils;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/10/30 15:55
 * @version: v1.0
 */
public class BooleanConverter implements Converter<Boolean> {

    public BooleanConverter(){
        register(boolean.class, this);
        register(Boolean.class, this);
    }

    @Override
    public Boolean convertToJavaData(ConvertData value) {
        return NumberUtils.parseBoolean((String) value.getSource(), value);
    }

    @Override
    public String convertToString(ConvertData value) {
        return String.valueOf(value.getSource());
    }
}
