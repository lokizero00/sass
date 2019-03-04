package com.loki.sass.service.utils.controller;

import com.loki.sass.common.code.AuthResultCode;
import com.loki.sass.common.dto.CurrentUserInfo;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.exception.UnknownLoginException;
import com.loki.sass.service.utils.api.AuthTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by lokizero00 on 2019-02-15
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthTokenController {
    @Autowired
    AuthTokenService authTokenService;

    @RequestMapping(value = "v1/verifyToken",method = RequestMethod.POST)
    public Boolean verifyToken(@RequestParam String token) throws BizException{
        if (StringUtils.isEmpty(token)){
            log.info("校验失败，token is null");
            throw new BizException(AuthResultCode.AUTH_NULL_ERROR);
        }
        try {
            return authTokenService.verifyToken(token);
        }catch(Exception e){
            log.info("token校验失败,token="+token);
            throw new BizException(AuthResultCode.AUTH_VERIFY_ERROR);
        }
    }

    @RequestMapping(value = "v1/authLogin",method = RequestMethod.POST)
    public CurrentUserInfo authLogin(@RequestParam String token, @RequestParam String tokenType) throws UnknownLoginException{
        if (StringUtils.isEmpty(token)){
            log.info("校验失败，token is null");
            throw new BizException(AuthResultCode.AUTH_NULL_ERROR);
        }
        if (StringUtils.isEmpty(tokenType)){
            log.info("校验失败，tokenType is null");
            throw new BizException(AuthResultCode.AUTH_NULL_ERROR);
        }
        try{
            CurrentUserInfo currentUserInfo=authTokenService.authLogin(token,tokenType);
            return currentUserInfo;
        }catch(UnknownLoginException e){
            log.info("token校验失败,token="+token+",tokenType="+tokenType);
            throw e;
        }
    }

    @RequestMapping(value = "v1/getToken",method = RequestMethod.POST)
    public String getToken(String appkey,String secret) throws Exception{
        return authTokenService.getToken(appkey,secret);
    }
}
