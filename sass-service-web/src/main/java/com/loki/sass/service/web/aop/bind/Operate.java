package com.loki.sass.service.web.aop.bind;

import java.lang.annotation.*;

/**
 * 方法描述
 * Created by lokizero00
 * date: 2018-10-25
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Operate {
    String value() default "";
    boolean isLogParams() default true;
}