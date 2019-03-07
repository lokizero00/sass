package com.loki.sass.feignclient.feignService;

import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * created by lokizero00 on 2019-02-20
 */
@FeignClient("sass-service-manager")
public interface FeignPermissionService {
    @RequestMapping(method = RequestMethod.POST, value = "/permission/v1/selectByRoleId")
    public ResultDTO<?> selectByRoleId(@RequestParam("roleId") Integer roleId)throws BizException;

    @RequestMapping(value= "/permission/v1/addPermission", method = RequestMethod.POST)
    public ResultDTO<?> addPermission(@RequestParam("permissionVOJson") String permissionVOJson,
                                 @RequestParam("operatorId")Integer operatorId)throws BizException;

    @RequestMapping(value="/permission/v1/editPermission",method = RequestMethod.POST)
    public ResultDTO<?> editPermission(@RequestParam("permissionVOJson") String permissionVOJson,
                                  @RequestParam("operatorId")Integer operatorId)throws BizException;

    @RequestMapping(value="/permission/v1/deletePermission",method = RequestMethod.POST)
    public ResultDTO<?> deletePermission(@RequestParam("id") Integer id,
                                    @RequestParam("operatorId") Integer operatorId)throws BizException;

    @RequestMapping(value="/permission/v1/findOne",method = RequestMethod.POST)
    public ResultDTO<?> findOne(@RequestParam("id") Integer id)throws BizException;

    @RequestMapping(value="/permission/v1/findAll",method = RequestMethod.POST)
    public ResultDTO<?> findAll()throws BizException;
}
