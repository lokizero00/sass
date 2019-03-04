package com.loki.sass.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lokizero00
 * Date: 2018/4/26 0026
 * Time: 17:19
 */
@Slf4j
public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String objectToJson(Object data){
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("Bean转Json出错");
        }
        return null;
    }

    public static <T> T jsonToObject(String jsonData,Class<T> beanType){
        try {
            return mapper.readValue(jsonData,beanType);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("JSON转Bean出错");
        }
        return null;
    }

    public static <T>List<T> jsonToList(String jsonData,Class<T> beanType){
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class,beanType);
        try {
            return mapper.readValue(jsonData,javaType);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("将json数据转换成pojo对象list");
        }
        return null;
    }

    public static String mapToString(Map map){
        try {
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("map转string错误:" + e.getMessage());
        }
        return null;
    }

    public static Map jsonStrToMap(String jsonStr){
        try {
            return mapper.readValue(jsonStr,Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("jsonStr转map错误:" + e.getMessage());
        }
        return null;
    }
}
