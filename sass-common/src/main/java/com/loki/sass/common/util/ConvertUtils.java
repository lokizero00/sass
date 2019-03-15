/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.loki.sass.common.util;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.util.interfaces.IConvertHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 转换工具类
 *
 * @author Mark sunlightcs@gmail.com
 */
public class ConvertUtils {
    private static Logger logger = LoggerFactory.getLogger(ConvertUtils.class);

    public static <T> T sourceToTarget(Object source, Class<T> target){
        if(source == null){
            return null;
        }
        T targetObject = null;
        try {
            targetObject = target.newInstance();
            BeanUtils.copyProperties(source, targetObject);
        } catch (Exception e) {
            logger.error("convert error ", e);
        }

        return targetObject;
    }

    public static <T> List<T> sourceToTarget(Collection<?> sourceList, Class<T> target){
        if(sourceList == null){
            return null;
        }

        List targetList = new ArrayList<>(sourceList.size());
        try {
            for(Object source : sourceList){
                T targetObject = target.newInstance();
                BeanUtils.copyProperties(source, targetObject);
                targetList.add(targetObject);
            }
        }catch (Exception e){
            logger.error("convert error ", e);
        }

        return targetList;
    }

    /**
     * 针对集合类型,如果转换前后类型字段不一致,可以用这个方法
     * @param collection        待转换集合
     * @param targetClass       转换目标类型
     * @param convertHelper    默认只要覆盖afterConvert
     * @param <T>
     * @return
     */
    public static<T> List<T> sourceToTarget(Collection<?> collection, Class<T> targetClass, IConvertHelper convertHelper){
        if(convertHelper==null){
            return sourceToTarget(collection,targetClass);
        }
        if(collection == null){
            return null;
        }
        List<T> list = new ArrayList<>();
        try {
            for(Object source : collection){
                T target = targetClass.newInstance();
                convertHelper.beforeConvert(source,target);
                BeanUtils.copyProperties(source, target);
                convertHelper.afterConvert(source,target);
                list.add(target);
            }
        }catch (Exception e){
            logger.error("convert error ", e);
        }
        return list;
    }

    public static<S,T> PageInfo<T> sourceToTarget(PageInfo<S> pageInfo, Class<T> tClass){
        return sourceToTarget(pageInfo,tClass,null);
    }

    public static<S,T> PageInfo<T> sourceToTarget(PageInfo<S> pageInfo, Class<T> tClass,IConvertHelper convertHelper){
        List<T> targetList = sourceToTarget(pageInfo.getList(), tClass,convertHelper);
        PageInfo<T> rtnPage = new PageInfo<T>(targetList);
        BeanUtils.copyProperties(pageInfo,rtnPage);
        rtnPage.setList(targetList);
        return rtnPage;
    }

}