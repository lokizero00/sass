package com.loki.sass.api.web.aop.bind;

import java.lang.annotation.*;

/**
 * 每个类的功能描述
 * Created by lokizero00
 * date: 2018-10-25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Function {
    String value() default "";
    String moduleName() default "";
    String subModuleName() default "";
}
