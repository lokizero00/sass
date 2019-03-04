package com.loki.sass.feignclient.feignService;

import com.loki.sass.common.dto.AdminDTO;
import com.loki.sass.common.dto.PermissionDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-20
 */
@FeignClient("sass-service-manager")
public interface FeignPermissionService {
    @RequestMapping(method = RequestMethod.POST, value = "/permission/v1/selectByRoleId")
    List<PermissionDTO> selectByRoleId(@RequestParam("roleId") Integer roleId);
}
