package com.loki.sass.service.utils.api;

import com.loki.sass.common.dto.CurrentUserInfo;
import com.loki.sass.common.dto.TokenDTO;
import com.loki.sass.common.exception.UnknownLoginException;

/**
 * created by lokizero00 on 2019-02-15
 */
public interface AuthTokenService {
    /**
     * 校验token是否合法
     * @param token
     * @return
     *
     * */
    boolean verifyToken(String token);
    /**
     * 解密token
     * @param token
     * @return
     *
     * */
    TokenDTO descToken(String token);
    /**
     * 获取token
     * @param appkey,secret
     * @return String
     *
     * */
    String getToken(String appkey, String secret) throws Exception;
    /**
     * 获取用户信息
     * @param token,tokenType
     * @return String
     *
     * */
    CurrentUserInfo authLogin(String token, String tokenType) throws UnknownLoginException;
}
