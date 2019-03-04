package com.loki.sass.service.utils.config;

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
public class SmsConfig {

    private static SmsConfig smsConfig = null;

    private SmsConfig(){}

    private @Value("${system.common.sms-duration}") Integer smsDuration;
    private @Value("${system.common.sms-today-limit}") Integer smsTodayLimit;
    private @Value("${system.common.sms-valid-time}") Integer smsValidTime;

    @Bean
    public static SmsConfig getInstance() {
        if(smsConfig == null)
            smsConfig = new SmsConfig();
        return smsConfig;
    }
}
