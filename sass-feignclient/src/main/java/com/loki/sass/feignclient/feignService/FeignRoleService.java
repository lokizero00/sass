package com.loki.sass.feignclient.feignService;

import com.loki.sass.common.dto.AdminDTO;
import com.loki.sass.common.dto.RoleDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-20
 */
@FeignClient("sass-service-manager")
public interface FeignRoleService {
    @RequestMapping(method = RequestMethod.POST, value = "/role/v1/selectByUserId")
    List<RoleDTO> selectByUserId(@RequestParam("adminId") Integer adminId);
}
