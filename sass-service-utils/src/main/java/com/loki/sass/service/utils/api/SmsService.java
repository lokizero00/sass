package com.loki.sass.service.utils.api;

import com.loki.sass.common.dto.SmsStatus;
import com.loki.sass.common.exception.BizException;

import java.util.Map;

/**
 * created by lokizero00 on 2019-02-15
 */
public interface SmsService {

    /**
     * 发送验证码短信接口
     * 每次时间间隔90s，每天每手机号发送不超过10次
     * @param mobile 手机号码、短信模版参数必须设置
     * @param randomCode 短信验证码code、短信模版参数必须设置
     * */
    public boolean sendCodeSMS(final String mobile, final String randomCode) throws BizException;

    /**
     * 普通单发短信
     * @param mobile           电话
     * @param map              信息
     * @param templateCode    模板
     * @return
     */
    public boolean sendMsg(String mobile, Map<String, String> map, String templateCode, String str);

    /**
     * 发送语音验证码接口
     * 每次时间间隔90s，每天每手机号发送不超过10次
     * @param mobile 手机号码、短信模版参数必须设置
     * @param randomCode 短信验证码code、短信模版参数必须设置
     * */
    public  boolean sendCodeTts(final String mobile, final String randomCode) throws Exception;

    /**
     * 短信验证码是否正确
     * @param mobile
     * @param smsCaptcha
     *
     * */
    public String confirmSmsCaptcha(final String mobile, final String smsCaptcha);

    public String allownSend(String mobile) throws RuntimeException;

    /**
     *
     * @param smsStatus  mobile smsParam必须设置
     * @return
     */
    public String updateSum(SmsStatus smsStatus);
}
