package com.loki.sass.service.web.handler;

/**
 * created by lokizero00 on 2019-02-15
 */

import com.loki.sass.common.code.CommonResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.feignclient.feignService.FeignTipInfoService;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

/**
 * 全局异常消息处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private FeignTipInfoService feignTipInfoService;

    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    public ResultDTO<?> jsonErrorHandler(HttpServletRequest req, BizException e) throws Exception {
        ResultDTO<String> rs = new ResultDTO<>();
        String resultCode=e.getErrorCode();
        String errorMessage=e.getErrorMsg();
        //非动态消息
        if(!e.isDynamic()){

            errorMessage=this.feignTipInfoService.getTipMsg(resultCode);

            if(null != e.getErrors() && e.getErrors().length > 0){
                MessageFormat messageFormat = new MessageFormat(errorMessage);
                errorMessage = messageFormat.format(e.getErrors());
            }
        }

        rs.setErrorMessage(errorMessage);
        rs.setResultCode(resultCode);
        rs.setModule(null);
        rs.setSuccess(false);
        return rs;
    }

    /**
     * shiro权限异常处理
     * @return
     */
    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public ResultDTO<?> unauthorizedException(UnauthorizedException e){
        ResultDTO<String> result = new ResultDTO<>();
        result.setResultCode(CommonResultCode.COMMON_UNAUTH);
        result.setErrorMessage(this.feignTipInfoService.getTipMsg(CommonResultCode.COMMON_UNAUTH));
        result.setModule(null);
        result.setSuccess(false);
        return result;
    }

    @ResponseBody
    @ExceptionHandler(UnauthenticatedException.class)
    public ResultDTO<?> unauthenticatedException(UnauthenticatedException e){
        ResultDTO<String> result = new ResultDTO<>();
        result.setResultCode(CommonResultCode.COMMON_LOGIN_TIMEOUT);
        result.setErrorMessage(this.feignTipInfoService.getTipMsg(CommonResultCode.COMMON_LOGIN_TIMEOUT));
        result.setModule(null);
        result.setSuccess(false);
        return result;
    }
}
