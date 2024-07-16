package com.small.rose.demo.txt.annotation.format;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.RoundingMode;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface NumberFormatFiled {

    /**
     *
     * Specific format reference {@link java.text.DecimalFormat}
     *
     * @return Format pattern
     */
    String value() default "";

    /**
     * Rounded by default
     *
     * @return RoundingMode
     */
    RoundingMode roundingMode() default RoundingMode.HALF_UP;
}
