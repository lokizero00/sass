package com.loki.sass.service.utils.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loki.sass.common.constant.Constants;
import com.loki.sass.common.dto.CurrentUserInfo;
import com.loki.sass.common.dto.TokenDTO;
import com.loki.sass.common.exception.UnknownLoginException;
import com.loki.sass.service.utils.api.AuthTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * created by lokizero00 on 2019-02-15
 */

@Slf4j
@Service
public class AuthTokenServiceImpl implements AuthTokenService {

    private @Value("${system.api.token.app-secret}") String appSsecret;

    private @Value("${system.api.token.app-issuer}") String appIssuer;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean verifyToken(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(appSsecret))
                    .withIssuer(appIssuer)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public TokenDTO descToken(String token) {
        TokenDTO tokenDto = new TokenDTO();
        JWT jwt = JWT.decode(token);
        tokenDto.setAppkey(jwt.getClaim(Constants.APP_KEY).asString());
        tokenDto.setSecret(jwt.getClaim(Constants.APP_SECRET).asString());
        return tokenDto;
    }

    @Override
    public String getToken(String appkey,String secret) throws Exception{
        String token = JWT.create()
                .withIssuer(appIssuer)
                .withClaim(Constants.APP_KEY, appkey)
                .withClaim(Constants.APP_SECRET,secret)
                .sign(Algorithm.HMAC256(appSsecret));
        return token;
    }

    @Override
    public CurrentUserInfo authLogin(String token, String tokenType) throws UnknownLoginException {

        TokenDTO tokenDto = descToken(token);
        if (tokenDto == null || StringUtils.isEmpty(tokenDto.getAppkey()) || StringUtils.isEmpty(tokenDto.getSecret())) {
            throw new UnknownLoginException("无效凭证，请重新登录");
        }
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        String json = valueOperations.get(tokenType+tokenDto.getAppkey());
        if(StringUtils.isBlank(json)){
            throw new UnknownLoginException("无效凭证，请重新登录");
        }
        ObjectMapper mapper = new ObjectMapper();
        CurrentUserInfo currentUserInfo = null;
        try {
            currentUserInfo = mapper.readValue(json,CurrentUserInfo.class);
        } catch (Exception e){
            throw new UnknownLoginException("无效凭证，请重新登录");
        }
        if(tokenDto.getAppkey() != null && currentUserInfo != null && tokenDto.getSecret().equals(currentUserInfo.getSecret())){
            return currentUserInfo;
        } else {
            throw new UnknownLoginException("无效凭证，请重新登录");
        }
    }
}
