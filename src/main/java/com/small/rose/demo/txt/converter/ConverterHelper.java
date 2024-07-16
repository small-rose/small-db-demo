package com.small.rose.demo.txt.converter;


import com.small.rose.demo.txt.converter.strategy.Converter;
import com.small.rose.demo.txt.converter.strategy.impl.BigDecimalConverter;
import com.small.rose.demo.txt.converter.strategy.impl.BigIntegerConverter;
import com.small.rose.demo.txt.converter.strategy.impl.BooleanConverter;
import com.small.rose.demo.txt.converter.strategy.impl.ByteConverter;
import com.small.rose.demo.txt.converter.strategy.impl.CharConverter;
import com.small.rose.demo.txt.converter.strategy.impl.DateConverter;
import com.small.rose.demo.txt.converter.strategy.impl.DoubleConverter;
import com.small.rose.demo.txt.converter.strategy.impl.FloatConverter;
import com.small.rose.demo.txt.converter.strategy.impl.IntegerConverter;
import com.small.rose.demo.txt.converter.strategy.impl.LocalDateConverter;
import com.small.rose.demo.txt.converter.strategy.impl.LocalDateTimeConverter;
import com.small.rose.demo.txt.converter.strategy.impl.LongConverter;
import com.small.rose.demo.txt.converter.strategy.impl.ShortConverter;
import com.small.rose.demo.txt.converter.strategy.impl.StringConverter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/10/30 16:41
 * @version: v1.0
 */
public class ConverterHelper {

    private static Map<String, Converter> cacheConverter = new HashMap<>();


    public static void initConverters(Map<Integer, Field> beanFieldMap) {

        cacheConverter.put("int", new IntegerConverter());
        cacheConverter.put("bigInteger", new BigIntegerConverter());
        cacheConverter.put("long", new LongConverter());
        cacheConverter.put("byte", new ByteConverter());
        cacheConverter.put("char", new CharConverter());
        cacheConverter.put("float", new FloatConverter());
        cacheConverter.put("double", new DoubleConverter());
        cacheConverter.put("short", new ShortConverter());
        cacheConverter.put("boolean", new BooleanConverter());
        cacheConverter.put("BigDecimal", new BigDecimalConverter());
        cacheConverter.put("Date", new DateConverter());
        cacheConverter.put("LocalDate", new LocalDateConverter());
        cacheConverter.put("LocalDateTime", new LocalDateTimeConverter());
        cacheConverter.put("String", new StringConverter());
    }
}
