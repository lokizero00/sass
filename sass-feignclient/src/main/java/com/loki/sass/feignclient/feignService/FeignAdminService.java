package com.loki.sass.feignclient.feignService;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.AdminDTO;
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
public interface FeignAdminService {
    @RequestMapping(method = RequestMethod.POST, value = "/admin/v1/selectByMobile")
    ResultDTO<AdminDTO> selectByMobile(@RequestParam("mobile") String mobile)throws BizException;

    @RequestMapping(method = RequestMethod.POST, value = "/admin/v1/addAdmin")
    public ResultDTO<Boolean> addAdmin(@RequestParam("adminRequestVOJson") String adminRequestVOJson)throws BizException;

    @RequestMapping(method = RequestMethod.POST, value = "/admin/v1/editAdmin")
    public ResultDTO<Boolean> editAdmin(@RequestParam("adminRequestVOJson") String adminRequestVOJson)throws BizException;

    @RequestMapping(method = RequestMethod.POST,value="/admin/v1/deleteAdmin")
    public ResultDTO<Boolean> deleteAdmin(@RequestParam("id") Integer id,@RequestParam("operatorId") Integer operatorId)throws BizException;

    @RequestMapping(method = RequestMethod.POST,value="admin/v1/findOne")
    public ResultDTO<AdminDTO> findOne(@RequestParam("id") Integer id)throws BizException;

    @RequestMapping(method = RequestMethod.POST,value="admin/v1/findAll")
    public ResultDTO<List<AdminDTO>> findAll()throws BizException;

    @RequestMapping(method = RequestMethod.POST,value="admin/v1/findByPage")
    public ResultDTO<PageInfo<AdminDTO>> findByPage(@RequestParam("adminQueryVOJson") String adminQueryVOJson)throws BizException;

    @RequestMapping(value="admin/v1/findRolesByAdminId",method = RequestMethod.POST)
    public ResultDTO<List<RoleDTO>> findRolesByAdminId(@RequestParam("adminId")Integer adminId)throws BizException;

    @RequestMapping(value="admin/v1/updateAdminRoles",method = RequestMethod.POST)
    public ResultDTO<Boolean> updateAdminRoles(@RequestParam("adminRoleRequestVOJson")String adminRoleRequestVOJson)throws BizException;
}
