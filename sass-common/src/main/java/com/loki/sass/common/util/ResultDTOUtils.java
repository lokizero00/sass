package com.loki.sass.common.util;

import com.loki.sass.common.dto.ResultDTO;

public class ResultDTOUtils {

    private ResultDTOUtils(){}

    public static <T> ResultDTO<T> success(T data){
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setModule(data);
        resultDTO.setSuccess(true);
        return resultDTO;
    }

    public static <T> ResultDTO<T> fail(T data){
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setModule(data);
        resultDTO.setSuccess(false);
        return resultDTO;
    }
}
