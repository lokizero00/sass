package com.loki.sass.feignclient.feignService;

import com.loki.sass.common.dto.WechatUserInfoDTO;
import com.loki.sass.common.exception.BizException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * created by lokizero00 on 2019-02-17
 */
@FeignClient("sass-service-payment")
public interface FeignWechatLoginService {
    @RequestMapping(method = RequestMethod.POST, value = "/wechat/v1/requestWechatLogin")
    public WechatUserInfoDTO requestWechatLogin(@RequestParam("code") String code) throws BizException;
}
