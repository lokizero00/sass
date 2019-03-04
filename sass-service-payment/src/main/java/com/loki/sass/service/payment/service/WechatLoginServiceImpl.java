package com.loki.sass.service.payment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.loki.sass.common.code.WechatResultCode;
import com.loki.sass.common.dto.WechatLoginDTO;
import com.loki.sass.common.dto.WechatUserInfoDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.service.payment.api.WechatLoginService;
import com.loki.sass.service.payment.config.WechatConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * created by lokizero00 on 2019-02-17
 */
@Slf4j
@Service
public class WechatLoginServiceImpl implements WechatLoginService {
    @Override
    public WechatUserInfoDTO requestWechatLogin(String code) throws BizException {
        try{
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WechatConfig.getInstance().getWXAppId() +
                    "&secret=" + WechatConfig.getInstance().getWXSecret() +
                    "&code=" + code +
                    "&grant_type=authorization_code";
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            ObjectMapper objectMapper = new ObjectMapper();
            WechatLoginDTO wechatLoginDto = null;

            if(entity != null){
                String result = EntityUtils.toString(entity,"UTF-8");
                wechatLoginDto = objectMapper.readValue(result, WechatLoginDTO.class);
                if(wechatLoginDto == null)
                    throw new BizException(WechatResultCode.WECHAT_LOGIN_ERROR);
            }else {
                throw new BizException(WechatResultCode.WECHAT_LOGIN_ERROR);
            }

            url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + wechatLoginDto.getAccess_token() +
                    "&openid=" + wechatLoginDto.getOpenid();
            httpGet = new HttpGet(url);
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            String result = EntityUtils.toString(entity,"UTF-8");
            return new Gson().fromJson(result, WechatUserInfoDTO.class);
        }catch(IOException e){
            e.printStackTrace();
            throw new BizException(WechatResultCode.WECHAT_LOGIN_ERROR);
        }

    }
}
