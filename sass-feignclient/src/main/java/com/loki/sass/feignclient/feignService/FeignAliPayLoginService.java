package com.loki.sass.feignclient.feignService;

import com.loki.sass.common.dto.AliRequestLoginDTO;
import com.loki.sass.common.exception.BizException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * created by lokizero00 on 2019-02-20
 */
@FeignClient("sass-service-payment")
public interface FeignAliPayLoginService {
    @RequestMapping(method = RequestMethod.POST, value = "/alipay/v1/requestAliLoginAuth")
    public AliRequestLoginDTO requestAliLoginAuth(@RequestParam("userId") String userId, @RequestParam("code") String code) throws BizException;

    @RequestMapping(method = RequestMethod.POST, value = "/alipay/v1/getAlipaySign")
    public String getAlipaySign() throws BizException;
}
