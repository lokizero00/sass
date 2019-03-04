package com.loki.sass.service.payment.controller;

import com.loki.sass.common.dto.AliRequestLoginDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.service.payment.api.AliPayLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by lokizero00 on 2019-02-18
 */
@Slf4j
@RestController
@RequestMapping("/aliPay")
public class AliPayController {
    @Autowired
    AliPayLoginService aliPayLoginService;

    @RequestMapping(value = "v1/requestAliLoginAuth",method = RequestMethod.POST)
    public AliRequestLoginDTO requestAliLoginAuth(@RequestParam String userId, @RequestParam String code) throws BizException {
        return aliPayLoginService.requestAliLoginAuth(userId,code);
    }

    @RequestMapping(value = "v1/getAlipaySign",method = RequestMethod.POST)
    public String getAlipaySign() throws BizException{
        return aliPayLoginService.getAlipaySign();
    }
}
