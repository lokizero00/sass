package com.loki.sass.service.payment.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.loki.sass.common.code.AliPayResultCode;
import com.loki.sass.common.dto.AliRequestLoginDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.OrderNoGenerator;
import com.loki.sass.service.payment.api.AliPayLoginService;
import com.loki.sass.service.payment.config.AliPayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;

/**
 * created by lokizero00 on 2019-02-18
 */
@Slf4j
@Service
public class AliPayLoginServiceImpl implements AliPayLoginService {

    @Transactional
    @Override
    public String getAlipaySign() throws BizException {
        try{
            String targetId = OrderNoGenerator.getPayOrderNo(1);
            String infoStr = "apiname=com.alipay.account.auth" +
                    "&app_id=" + AliPayConfig.getInstance().getAliAppId() +
                    "&app_name=mc" +
                    "&auth_type=AUTHACCOUNT" +
                    "&biz_type=openservice" +
                    "&method=alipay.open.auth.sdk.code.get" +
                    "&pid=" + AliPayConfig.getInstance().getPID() +
                    "&product_id=APP_FAST_LOGIN" +
                    "&scope=kuaijie" +
                    "&sign_type=RSA2" +
                    "&target_id=" + targetId +
                    "&sign_type=RSA2";
            String sign = AlipaySignature.rsaSign(infoStr, AliPayConfig.getInstance().getAliAppPrivateKey(), "UTF-8","RSA2");
            infoStr = infoStr + "&sign=" + URLEncoder.encode(sign,"UTF-8");
            return infoStr;
        }catch(Exception e){
            e.printStackTrace();
            throw new BizException(AliPayResultCode.ALIPAY_SIGN_ERROR);
        }
    }

    @Transactional
    @Override
    public AliRequestLoginDTO requestAliLoginAuth(String userId, String code) throws BizException {
        try{
            AliRequestLoginDTO aliRequestLoginDTO=new AliRequestLoginDTO();
            AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",AliPayConfig.getInstance().getAliAppId(),AliPayConfig.getInstance().getAliAppPrivateKey(),"json","GBK",AliPayConfig.getInstance().getAliAppPublicKey(),"RSA2");
            AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();//创建API对应的request类
            request.setGrantType("authorization_code");
            request.setCode(code);
            AlipaySystemOauthTokenResponse response = alipayClient.execute(request);

            if(response.isSuccess()){
                AlipayUserInfoShareRequest requestUser = new AlipayUserInfoShareRequest();
                AlipayUserInfoShareResponse userinfoShareResponse = alipayClient.execute(requestUser, response.getAccessToken());
                if(userinfoShareResponse!=null){
                    aliRequestLoginDTO.setAliAccount(userinfoShareResponse.getUserId());
                    aliRequestLoginDTO.setAliNickName(userinfoShareResponse.getNickName());
                    aliRequestLoginDTO.setAliAvatar(userinfoShareResponse.getAvatar());
                    aliRequestLoginDTO.setAccess_token(response.getAccessToken());
                    aliRequestLoginDTO.setExpires_in(response.getExpiresIn());
                    aliRequestLoginDTO.setRe_expires_in(response.getReExpiresIn());
                    aliRequestLoginDTO.setRefresh_token(response.getRefreshToken());

                    return aliRequestLoginDTO;
                }
            }
            return null;
        }catch(AlipayApiException e){
            e.printStackTrace();
            throw new BizException(AliPayResultCode.ALIPAY_LOGIN_ERROR);
        }
    }
}
