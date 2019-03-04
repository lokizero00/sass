package com.loki.sass.feignclient.feignService;

import com.loki.sass.common.dto.SmsStatus;
import com.loki.sass.common.exception.BizException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * created by lokizero00 on 2019-02-15
 */
@FeignClient("sass-service-utils")
public interface FeignSmsService {

    @RequestMapping(method = RequestMethod.POST, value = "/sms/v1/sendCodeSMS")
    public boolean sendCodeSMS(@RequestParam("mobile") String mobile, @RequestParam("randomCode") String randomCode) throws BizException;

    @RequestMapping(method = RequestMethod.POST, value = "/sms/v1/sendMsg")
    public boolean sendMsg(@RequestParam("mobile") String mobile, @RequestParam("map") Map<String, String> map, @RequestParam("templateCode") String templateCode, @RequestParam("str") String str);

    @RequestMapping(method = RequestMethod.POST, value = "/sms/v1/confirmSmsCaptcha")
    public String confirmSmsCaptcha(@RequestParam("mobile") String mobile, @RequestParam("smsCaptcha") String smsCaptcha);

    @RequestMapping(method = RequestMethod.POST, value = "/sms/v1/allownSend")
    public String allownSend(@RequestParam("mobile") String mobile) throws RuntimeException;

    @RequestMapping(method = RequestMethod.POST, value = "/sms/v1/updateSum")
    public String updateSum(@RequestBody SmsStatus smsStatus);
}
