package com.loki.sass.feignclient.feignService;

import com.loki.sass.common.dto.AdminDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * created by lokizero00 on 2019-02-20
 */
@FeignClient("sass-service-manager")
public interface FeignAdminService {
    @RequestMapping(method = RequestMethod.POST, value = "/admin/v1/selectByMobile")
    public AdminDTO selectByMobile(@RequestParam("mobile") String mobile);
}
