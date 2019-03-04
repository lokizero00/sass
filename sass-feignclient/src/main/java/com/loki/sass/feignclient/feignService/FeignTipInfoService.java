package com.loki.sass.feignclient.feignService;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * created by lokizero00 on 2019-02-15
 */

@FeignClient("sass-service-sysconfig")
public interface FeignTipInfoService {
    @RequestMapping(method = RequestMethod.POST, value = "/tipInfo/v1/getTipMsg")
    public String getTipMsg(@RequestParam("code") String code);
}
