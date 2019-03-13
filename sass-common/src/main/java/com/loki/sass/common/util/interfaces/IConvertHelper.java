package com.loki.sass.common.util.interfaces;

/**
 * 协助ConvertUtils完成转换操作,主要针对集合类型.
 * 当每一个被转换对象被添加到集合前,执行afterConvert方法.
 * 从而将无法转换的属性,根据afterConvert逻辑附加上值
 * @param <S>   转换源的类型
 * @param <T>   转换目标的类型
 */
public interface IConvertHelper<S,T> {

    default void beforeConvert(S s,T t){
        //默认无需覆盖此方法
    };

    void afterConvert(S s,T t);
    
}
