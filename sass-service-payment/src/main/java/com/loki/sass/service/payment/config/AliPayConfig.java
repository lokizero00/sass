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
public class AliPayConfig {

    private static AliPayConfig aliPayConfig = null;

    private AliPayConfig(){}

    private @Value("${system.bill.aliPay.appPrivateKey}") String AliAppPrivateKey;

    private @Value("${system.bill.aliPay.appPublicKey}") String AliAppPublicKey;

    private @Value("${system.bill.aliPay.PID}") String PID;

    private @Value("${system.bill.aliPay.appId}") String AliAppId;

    @Bean
    public static AliPayConfig getInstance() {
        if(aliPayConfig == null)
            aliPayConfig = new AliPayConfig();
        return aliPayConfig;
    }
}
