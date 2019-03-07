package com.loki.sass.feignclient.feignService;

import com.loki.sass.common.dto.PermissionDTO;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
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
    public ResultDTO<List<PermissionDTO>> selectByRoleId(@RequestParam("roleId") Integer roleId)throws BizException;

    @RequestMapping(value= "/permission/v1/addPermission", method = RequestMethod.POST)
    public ResultDTO<Boolean> addPermission(@RequestParam("permissionRequestVOJson") String permissionRequestVOJson,
                                            @RequestParam("operatorId")Integer operatorId)throws BizException;

    @RequestMapping(value="/permission/v1/editPermission",method = RequestMethod.POST)
    public ResultDTO<Boolean> editPermission(@RequestParam("permissionRequestVOJson") String permissionRequestVOJson,
                                            @RequestParam("operatorId")Integer operatorId)throws BizException;

    @RequestMapping(value="/permission/v1/deletePermission",method = RequestMethod.POST)
    public ResultDTO<Boolean> deletePermission(@RequestParam("id") Integer id,
                                                @RequestParam("operatorId") Integer operatorId)throws BizException;

    @RequestMapping(value="/permission/v1/findOne",method = RequestMethod.POST)
    public ResultDTO<PermissionDTO> findOne(@RequestParam("id") Integer id)throws BizException;

    @RequestMapping(value="/permission/v1/findAll",method = RequestMethod.POST)
    public ResultDTO<List<PermissionDTO>> findAll()throws BizException;
}
