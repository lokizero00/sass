package com.loki.sass.feignclient.feignService;

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
    public ResultDTO<?> addRole(@RequestParam("roleRequestVOJson") String roleRequestVOJson)throws BizException;

    @RequestMapping(value = "/role/v1/deleteRole",method = RequestMethod.POST)
    public ResultDTO<?> deleteRole(@RequestParam("id") Integer id,
                                   @RequestParam("operatorId") Integer operatorId)throws BizException;

    @RequestMapping(value = "/role/v1/editRole",method = RequestMethod.POST)
    public ResultDTO<?> editRole(@RequestParam("roleRequestVOJson") String roleRequestVOJson)throws BizException;

    @RequestMapping(value = "/role/v1/findOne",method = RequestMethod.POST)
    public ResultDTO<?> findOne(@RequestParam("id") Integer id)throws BizException;

    @RequestMapping(value = "/role/v1/findAll",method = RequestMethod.POST)
    public ResultDTO<?> findAll()throws BizException;

    @RequestMapping(value = "/role/v1/findByPage",method = RequestMethod.POST)
    public ResultDTO<?> findByPage(@RequestParam("roleQueryVOJson") String roleQueryVOJson)throws BizException;

}
