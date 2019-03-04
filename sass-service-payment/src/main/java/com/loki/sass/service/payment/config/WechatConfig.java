package com.loki.sass.service.payment.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by lokizero00
 * date 2019-02-12
 */
@Data
@Component
public class WechatConfig {

    private static WechatConfig wechatConfig = null;

    private WechatConfig(){}

    private @Value("${system.bill.weixinPay.appId}") String WXAppId;;
    private @Value("${system.bill.weixinPay.appSecret}") String WXSecret;

    @Bean
    public static WechatConfig getInstance() {
        if(wechatConfig == null)
            wechatConfig = new WechatConfig();
        return wechatConfig;
    }
}
