package com.loki.sass.service.utils.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loki.sass.common.code.SmsResultCode;
import com.loki.sass.common.constant.Constants;
import com.loki.sass.common.dto.SmsStatus;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.service.utils.api.SmsService;
import com.loki.sass.service.utils.config.SmsConfig;
import com.loki.sass.service.utils.util.sms.ali.AliSmsService;
import com.loki.sass.service.utils.util.sms.ali.ISmsRequest;
import com.loki.sass.service.utils.util.sms.ali.SmsRequest;
import com.loki.sass.service.utils.util.sms.qcloud.QcloudSmsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * created by lokizero00 on 2019-02-15
 */
@Slf4j
@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AliSmsService aliSmsService;

    @Autowired
    private QcloudSmsService qcloudSmsService;

    @Override
    public boolean sendCodeSMS(String mobile, String randomCode) throws BizException {
        ISmsRequest smsRequest = new SmsRequest();
        smsRequest.setRecNum(mobile);
        smsRequest.setSmsTemplateCode("SMS_120550240");
        String smsParam = "{'code':'"+ randomCode+"'}";
        smsRequest.setSmsParam(smsParam);
        try {
            return aliSmsService.sendSms(smsRequest);
        }catch (Exception e){
            try {
                ArrayList<String> smsParams = new ArrayList<>();
                smsParams.add(randomCode);
                return qcloudSmsService.sendWithParam(mobile,smsParams);
            }catch (Exception ex){
                log.error("阿里、腾讯短信验证码同时发送失败！");
                throw new BizException(SmsResultCode.SEND_SMS_ERROR);
            }
        }
    }

    @Override
    public boolean sendMsg(String mobile, Map<String, String> map, String templateCode, String str) {
        String smsParam = "{";
        for(Map.Entry<String, String> vo : map.entrySet()){
            smsParam += "'"+vo.getKey()+"':'"+ vo.getValue()+"',";
        }
        smsParam=smsParam.substring(0,smsParam.length()-1)+"}";

        ISmsRequest smsRequest=new SmsRequest();
        smsRequest.setRecNum(mobile);
        smsRequest.setSmsTemplateCode(templateCode);
        smsRequest.setSmsParam(smsParam);
        try {
            boolean flag=aliSmsService.sendSms(smsRequest);
            return flag;
        }catch (Exception e){
            try {
                return qcloudSmsService.sendSMS(mobile,str);
            }catch (Exception ex){
                log.error("阿里、腾讯短信同时发送失败！");
                throw new BizException(SmsResultCode.SEND_SMS_ERROR);
            }
        }
    }

    @Override
    public boolean sendCodeTts(String mobile, String randomCode) throws Exception {
        //        ITtsRequest ttsRequest = new RandomCodeTtsRequest();
        //        ttsRequest.setCalledNum(phone);
        //        ttsRequest.setTtsParam(randomCode);
        //        return aliSmsService.sendTts(ttsRequest);
        return true;
    }

    @Override
    public String confirmSmsCaptcha(String mobile, String smsCaptcha) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        if (StringUtils.isEmpty(smsCaptcha)) {
            return SmsResultCode.COMMON_VERIFY_CODE_ERROR;
        }
        try {
            //判断验证码是否正确
            String serverCaptcha = valueOperations.get(Constants.SMS_TITLE+mobile);
            if(null == serverCaptcha){
                return SmsResultCode.COMMON_VERIFY_CODE_ERROR;
            }
            ObjectMapper mapper = new ObjectMapper();
            SmsStatus sum = mapper.readValue(serverCaptcha,SmsStatus.class);
            if (StringUtils.isEmpty(sum.getLastRandom()) || !sum.getLastRandom().equals(smsCaptcha)){
                return SmsResultCode.COMMON_VERIFY_CODE_ERROR;
            }
            //判断当前短信是否超出有效期，并且短信是否没有被使用过
            Long smsInterval =  (System.currentTimeMillis()-sum.getLast())/1000;
            int smsState = sum.getState();
            Integer smsValidTime = SmsConfig.getInstance().getSmsValidTime();
            if (smsValidTime > smsInterval && smsState == 1){
                sum.setState(0);
                valueOperations.set(Constants.SMS_TITLE+sum.getMobile(),mapper.writeValueAsString(sum));
                return SmsResultCode.TRUE;
            } else {
                return SmsResultCode.SMS_OUT_VALID_TIME;
            }
        } catch (Exception e) {
            return SmsResultCode.COMMON_SERVICE_ERROR;
        }
    }

    @Override
    public String allownSend(String mobile) throws RuntimeException {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        SmsStatus sum ;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = valueOperations.get(Constants.SMS_TITLE+mobile);
            if (StringUtils.isBlank(jsonStr)){
                return SmsResultCode.TRUE;
            } else {
                sum = mapper.readValue(jsonStr,SmsStatus.class);
                if(sum == null) {
                    return SmsResultCode.TRUE;
                }
            }
            long last = sum.getLast();
            int todayCnt = sum.getTodayCnt();
            //两次短信间隔小于90S 或当天发送大于10，限制用户短信发送
            Integer smsDuration = SmsConfig.getInstance().getSmsDuration();
            Integer smsTodayLimit = SmsConfig.getInstance().getSmsTodayLimit();
            if(System.currentTimeMillis()-last < smsDuration*1000) {
                return SmsResultCode.SMS_DURATION_ERROR;
            } else if(isSameDay(last) && todayCnt >=smsTodayLimit) {
                return SmsResultCode.SMS_TODAY_LIMIT_ERROR;
            }
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        } catch (Exception e){
            return SmsResultCode.SEND_SMS_ERROR;
        }
        return SmsResultCode.TRUE;
    }

    @Override
    public String updateSum(SmsStatus smsStatus) {
        assert smsStatus != null && StringUtils.isNotEmpty(smsStatus.getMobile());
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        String json  = valueOperations.get(Constants.SMS_TITLE+smsStatus.getMobile());
        SmsStatus sum = null;
        try {
            if(StringUtils.isNotBlank(json)) {
                ObjectMapper mapper = new ObjectMapper();
                sum = mapper.readValue(json,SmsStatus.class);
                if(!isSameDay(sum.getLast())){
                    sum.setTodayCnt(0);
                }
            } else {
                sum = new SmsStatus(smsStatus.getMobile());
            }
            sum.setLast(System.currentTimeMillis());
            sum.setMobile(smsStatus.getMobile());
            sum.setTodayCnt(sum.getTodayCnt()+1);
            sum.setLastRandom(smsStatus.getLastRandom());
            sum.setState(smsStatus.getState());
            ObjectMapper mapper = new ObjectMapper();
            valueOperations.set(Constants.SMS_TITLE+sum.getMobile(),mapper.writeValueAsString(sum));
        } catch (Exception e){
            return SmsResultCode.SEND_SMS_ERROR;
        }
        return SmsResultCode.TRUE;
    }

    private boolean isSameDay(long day){
        Date date = new Date(day);
        Date current = new Date(System.currentTimeMillis());
        return DateUtils.isSameDay(date,current);
    }
}
