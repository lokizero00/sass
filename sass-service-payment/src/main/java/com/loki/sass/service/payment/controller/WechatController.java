package com.loki.sass.service.payment.controller;

import com.loki.sass.common.dto.WechatUserInfoDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.service.payment.api.WechatLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by lokizero00 on 2019-02-17
 */
@Slf4j
@RestController
@RequestMapping("/wechat")
public class WechatController {
    @Autowired
    WechatLoginService wechatLoginService;

    @RequestMapping(value = "v1/requestWechatLogin",method = RequestMethod.POST)
    public WechatUserInfoDTO requestWechatLogin(@RequestParam String code) throws BizException {
        return wechatLoginService.requestWechatLogin(code);
    }
}
