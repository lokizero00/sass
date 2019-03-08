package com.loki.sass.feignclient.feignService;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.PermissionDTO;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.RoleDTO;
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
public interface FeignRoleService {
    @RequestMapping(method = RequestMethod.POST, value = "/role/v1/selectByUserId")
    public ResultDTO<List<RoleDTO>> selectByUserId(@RequestParam("adminId") Integer adminId)throws BizException;

    @RequestMapping(value = "/role/v1/addRole",method = RequestMethod.POST)
    public ResultDTO<Boolean> addRole(@RequestParam("roleRequestVOJson") String roleRequestVOJson)throws BizException;

    @RequestMapping(value = "/role/v1/deleteRole",method = RequestMethod.POST)
    public ResultDTO<Boolean> deleteRole(@RequestParam("id") Integer id,
                                   @RequestParam("operatorId") Integer operatorId)throws BizException;

    @RequestMapping(value = "/role/v1/editRole",method = RequestMethod.POST)
    public ResultDTO<Boolean> editRole(@RequestParam("roleRequestVOJson") String roleRequestVOJson)throws BizException;

    @RequestMapping(value = "/role/v1/findOne",method = RequestMethod.POST)
    public ResultDTO<RoleDTO> findOne(@RequestParam("id") Integer id)throws BizException;

    @RequestMapping(value = "/role/v1/findAll",method = RequestMethod.POST)
    public ResultDTO<List<RoleDTO>> findAll()throws BizException;

    @RequestMapping(value = "/role/v1/findByPage",method = RequestMethod.POST)
    public ResultDTO<PageInfo<RoleDTO>> findByPage(@RequestParam("roleQueryVOJson") String roleQueryVOJson)throws BizException;

    @RequestMapping(value = "/role/v1/hasPermission",method = RequestMethod.POST)
    public ResultDTO<Boolean> hasPermission(@RequestParam("rolePermissionRequestVOJson")String rolePermissionRequestVOJson)throws BizException;

    @RequestMapping(value = "/role/v1/addPermission",method = RequestMethod.POST)
    public ResultDTO<Boolean> addPermission(@RequestParam("rolePermissionRequestVOJson")String rolePermissionRequestVOJson)throws BizException;

    @RequestMapping(value = "/role/v1/deletePermissionByRecord",method = RequestMethod.POST)
    public ResultDTO<Boolean> deletePermissionByRecord(@RequestParam("rolePermissionRequestVOJson")String rolePermissionRequestVOJson)throws BizException;

    @RequestMapping(value="/role/v1/deletePermissionById",method = RequestMethod.POST)
    public ResultDTO<Boolean> deletePermissionById(@RequestParam("id") Integer id)throws BizException;

    @RequestMapping(value = "/role/v1/showPermissions",method = RequestMethod.POST)
    public ResultDTO<List<PermissionDTO>> showPermissions(Integer roleId)throws BizException;

}
