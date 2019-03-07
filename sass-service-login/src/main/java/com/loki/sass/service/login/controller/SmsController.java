package com.loki.sass.service.login.controller;

import com.loki.sass.common.code.SmsResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.SmsStatus;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.feignclient.feignService.FeignSmsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * created by lokizero00 on 2019-02-14
 */
@Slf4j
@RestController
@RequestMapping("/sms")
public class SmsController {
    @Autowired
    FeignSmsService feignSmsService;

//    @HystrixCommand(ignoreExceptions = BizException.class)
    @ApiOperation(value = "发送验证码短信",notes = "发送注册验证码短信")
    @RequestMapping(value = "v1/sendSmsCode",method = RequestMethod.POST)
    public ResultDTO<String> sendSmsCode(
            @ApiParam(name = "mobile", value = "手机号码") @RequestParam String mobile) throws BizException {
        ResultDTO<String> result=new ResultDTO<>();
        assert StringUtils.isNotEmpty(mobile);
        ResultDTO<String> allowResult = feignSmsService.allownSend(mobile);
        if(!allowResult.isSuccess()){
            throw new BizException(allowResult.getResultCode());
        }
        try {
            String randomCode = RandomStringUtils.randomNumeric(6);
            ResultDTO<Boolean> sendResult=feignSmsService.sendCodeSMS(mobile,randomCode);
            if (sendResult.isSuccess()){
                SmsStatus smsStatus = new SmsStatus(mobile);
                smsStatus.setLastRandom(randomCode);
                ResultDTO<String> updateResult=feignSmsService.updateSum(smsStatus);
                if(!updateResult.isSuccess()){
                    throw new BizException(SmsResultCode.SEND_SMS_ERROR);
                } else {
                    result.setModule(randomCode);
                    result.setSuccess(true);
                }
            } else {
                log.info(mobile+":发送注册短信失败");
                throw new BizException(SmsResultCode.SEND_SMS_ERROR);
            }
        }
        catch (Exception e) {
            log.error("发送短信失败：",e);
            throw new BizException(SmsResultCode.SEND_SMS_ERROR);
        }
        return result;
    }
}
