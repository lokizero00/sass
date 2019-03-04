package com.loki.sass.service.utils.util.sms.qcloud;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.loki.sass.common.code.SmsResultCode;
import com.loki.sass.common.exception.BizException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by lokizero00
 * date 2018-06-20
 */
@Slf4j
@Service
public class QcloudSmsService {

    public static final String NATION_CODE = "86";

    public static final Integer TMPL_ID  = 107738;

    /**
     * 发送短信接口
     * 每次时间间隔90s，每天每手机号发送不超过10次
     * @param phone 手机号码
     * @param msg 手机号码
     * */
    public boolean sendSMS(final String phone,final String msg) {
        try {
            QcloudSmsConfig smsConfig = QcloudSmsFactory.getInstance(QcloudSmsFactory.QCLOUD);
            SmsSingleSender singleSender = new SmsSingleSender(smsConfig.getAppid(), smsConfig.getAppkey());
            SmsSingleSenderResult singleSenderResult;
            //普通单发
            singleSenderResult = singleSender.send(0, NATION_CODE, phone, msg, "", "");
            log.info(singleSenderResult.toString());
            log.info("腾讯云发送短信成功");
        } catch (Exception e){
            log.info("腾讯云发送短信成功");
        }
        return true;
    }

    /**
     * 发送短信接口
     * 每次时间间隔90s，每天每手机号发送不超过10次
     * @param phone 手机号码
     * @param params 手机号码
     * */
    public boolean sendWithParam(final String phone,ArrayList<String> params) {
        try {
            QcloudSmsConfig smsConfig = QcloudSmsFactory.getInstance(QcloudSmsFactory.QCLOUD);
            SmsSingleSender singleSender = new SmsSingleSender(smsConfig.getAppid(), smsConfig.getAppkey());
            SmsSingleSenderResult singleSenderResult;
            //模板单发
            singleSenderResult = singleSender.sendWithParam(NATION_CODE, phone, TMPL_ID, params, "上海晅有信息科技有限公司", "","");
            log.debug("短信回执：->>>>>>>>>>>"+singleSenderResult.toString());
            if(0 == singleSenderResult.result) {
                log.info("腾讯云发送短信成功");
                return true;
            }
            log.info("腾讯云发送短信失败");
            throw new BizException(SmsResultCode.SEND_SMS_ERROR);
        } catch (Exception e){
            throw new BizException(SmsResultCode.SEND_SMS_ERROR);
        }
    }
}

interface QcloudSmsConfig {
    public Integer getAppid();
    public String getAppkey();
}


class QcloudSmsFactory{
    public static final String QCLOUD="QCLOUD";
    private static QcloudSmsConfig smsConfig;
    public static QcloudSmsConfig getInstance(String whichSmsProvider){
        assert StringUtils.isNoneEmpty(whichSmsProvider);
        if(whichSmsProvider.equals(QCLOUD)){
            if (smsConfig == null)
                smsConfig = new Qcloud2SmsConfig();
        }
        return smsConfig;
    }
    static final class Qcloud2SmsConfig implements QcloudSmsConfig {
        @Getter
        private Integer appid = 1400084005;
        @Getter
        private String appkey = "21d27014e52d3b915e5e1fe2977c7298";
    }

}


