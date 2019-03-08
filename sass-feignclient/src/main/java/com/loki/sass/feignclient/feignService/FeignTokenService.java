package com.loki.sass.feignclient.feignService;

import com.loki.sass.common.dto.CurrentUserInfo;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.exception.UnknownLoginException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * created by lokizero00 on 2019-02-15
 */

@FeignClient("sass-service-utils")
public interface FeignTokenService {
    @RequestMapping(method = RequestMethod.POST, value = "/auth/v1/verifyToken")
    ResultDTO<Boolean> verifyToken(@RequestParam("token") String token) throws BizException;

    @RequestMapping(method = RequestMethod.POST, value = "/auth/v1/authLogin")
    ResultDTO<CurrentUserInfo> authLogin(@RequestParam("token") String token, @RequestParam("tokenType") String tokenType) throws UnknownLoginException;

    @RequestMapping(method = RequestMethod.POST, value = "/auth/v1/getToken")
    ResultDTO<String> getToken(@RequestParam("appkey") String appkey, @RequestParam("secret") String secret) throws Exception;
}
