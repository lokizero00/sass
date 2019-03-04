package com.loki.sass.service.utils.controller;

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
    public boolean sendCodeSMS(@RequestParam String mobile, @RequestParam String randomCode) throws BizException {
        return smsService.sendCodeSMS(mobile,randomCode);
    }

    @RequestMapping(value = "v1/sendMsg",method = RequestMethod.POST)
    public boolean sendMsg(@RequestParam String mobile, @RequestParam Map<String, String> map, @RequestParam String templateCode, @RequestParam String str) {
        return smsService.sendMsg(mobile,map,templateCode,str);
    }

    @RequestMapping(value = "v1/confirmSmsCaptcha",method = RequestMethod.POST)
    public String confirmSmsCaptcha(@RequestParam String mobile, @RequestParam String smsCaptcha) {
        return smsService.confirmSmsCaptcha(mobile,smsCaptcha);
    }

    @RequestMapping(value = "v1/allownSend",method = RequestMethod.POST)
    public String allownSend(@RequestParam String mobile) throws RuntimeException {
        return smsService.allownSend(mobile);
    }

    @RequestMapping(value = "v1/updateSum",method = RequestMethod.POST)
    public String updateSum(@RequestBody SmsStatus smsStatus) {
        return smsService.updateSum(smsStatus);
    }
}
