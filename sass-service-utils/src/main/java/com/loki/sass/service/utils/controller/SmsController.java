package com.loki.sass.service.utils.controller;

import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.SmsStatus;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.service.utils.api.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * created by lokizero00 on 2019-02-15
 */
@Slf4j
@RestController
@RequestMapping("/sms")
public class SmsController {
    @Autowired
    SmsService smsService;

    @RequestMapping(value = "v1/sendCodeSMS",method = RequestMethod.POST)
    public ResultDTO<Boolean> sendCodeSMS(@RequestParam String mobile, @RequestParam String randomCode) throws BizException {
        ResultDTO<Boolean> result=new ResultDTO<>();
        result.setSuccess(smsService.sendCodeSMS(mobile,randomCode));
        return result;
    }

    @RequestMapping(value = "v1/sendMsg",method = RequestMethod.POST)
    public ResultDTO<Boolean> sendMsg(@RequestParam String mobile, @RequestParam Map<String, String> map, @RequestParam String templateCode, @RequestParam String str) {
        ResultDTO<Boolean> result=new ResultDTO<>();
        result.setSuccess(smsService.sendMsg(mobile,map,templateCode,str));
        return result;
    }

    @RequestMapping(value = "v1/confirmSmsCaptcha",method = RequestMethod.POST)
    public ResultDTO<String> confirmSmsCaptcha(@RequestParam String mobile, @RequestParam String smsCaptcha) {
        ResultDTO<String> result=new ResultDTO<>();
        result.setSuccess(true);
        result.setModule(smsService.confirmSmsCaptcha(mobile,smsCaptcha));
        return result;
    }

    @RequestMapping(value = "v1/allownSend",method = RequestMethod.POST)
    public ResultDTO<String> allownSend(@RequestParam String mobile) throws RuntimeException {
        ResultDTO<String> result=new ResultDTO<>();
        result.setSuccess(true);
        result.setModule(smsService.allownSend(mobile));
        return result;
    }

    @RequestMapping(value = "v1/updateSum",method = RequestMethod.POST)
    public ResultDTO<String> updateSum(@RequestBody SmsStatus smsStatus) {
        ResultDTO<String> result=new ResultDTO<>();
        result.setSuccess(true);
        result.setModule(smsService.updateSum(smsStatus));
        return result;
    }
}
