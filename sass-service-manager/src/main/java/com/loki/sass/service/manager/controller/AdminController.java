package com.loki.sass.service.manager.controller;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.AdminDTO;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.RoleDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.util.ResultDTOUtils;
import com.loki.sass.common.vo.AdminQueryVO;
import com.loki.sass.common.vo.AdminRequestVO;
import com.loki.sass.common.vo.AdminRoleRequestVO;
import com.loki.sass.service.manager.api.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-17
 */

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "v1/selectByMobile",method = RequestMethod.POST)
    public ResultDTO<AdminDTO> selectByMobile(@RequestParam("mobile") String mobile)throws BizException {
        return ResultDTOUtils.success(adminService.selectByMobile(mobile));
    }

    @RequestMapping(value= "v1/addAdmin", method = RequestMethod.POST)
    public ResultDTO<Boolean> insert(@RequestParam("adminRequestVOJson") String adminRequestVOJson)throws BizException{
        AdminRequestVO adminRequestVO = JsonUtils.jsonToObject(adminRequestVOJson,AdminRequestVO.class);
        return ResultDTOUtils.success(adminService.insert(adminRequestVO));
    }

    @RequestMapping(value="v1/editAdmin",method = RequestMethod.POST)
    public ResultDTO<Boolean> update(@RequestParam("adminRequestVOJson") String adminRequestVOJson)throws BizException{
        AdminRequestVO adminRequestVO = JsonUtils.jsonToObject(adminRequestVOJson,AdminRequestVO.class);
        return ResultDTOUtils.success(adminService.update(adminRequestVO));
    }

    @RequestMapping(value="v1/deleteAdmin",method = RequestMethod.POST)
    public ResultDTO<Boolean> delete(@RequestParam("id") Integer id,@RequestParam("operatorId") Integer operatorId)throws BizException{
        return ResultDTOUtils.success(adminService.deleteById(id,operatorId));
    }

    @RequestMapping(value="v1/findOne",method = RequestMethod.POST)
    public ResultDTO<AdminDTO> findOne(@RequestParam("id") Integer id)throws BizException{
        return ResultDTOUtils.success(adminService.findOne(id));
    }

    @RequestMapping(value="v1/findAll",method = RequestMethod.POST)
    public ResultDTO<List<AdminDTO>> findAll()throws BizException{
        return ResultDTOUtils.success(adminService.findAll());
    }

    @RequestMapping(value="v1/findByPage",method = RequestMethod.POST)
    public ResultDTO<PageInfo<AdminDTO>> findByPage(@RequestParam("adminQueryVOJson") String adminQueryVOJson)throws BizException{
        AdminQueryVO adminQueryVO = JsonUtils.jsonToObject(adminQueryVOJson, AdminQueryVO.class);
        return ResultDTOUtils.success(adminService.getAdminListSearch(adminQueryVO));
    }

    @RequestMapping(value="v1/findRolesByAdminId",method = RequestMethod.POST)
    public ResultDTO<List<RoleDTO>> findRolesByAdminId(@RequestParam("adminId")Integer adminId)throws BizException{
        return ResultDTOUtils.success(adminService.findRolesByAdminId(adminId));
    }

    @RequestMapping(value="v1/updateAdminRoles",method = RequestMethod.POST)
    public ResultDTO<Boolean> updateAdminRoles(@RequestParam("adminRoleRequestVOJson")String adminRoleRequestVOJson)throws BizException{
        AdminRoleRequestVO adminRoleRequestVO = JsonUtils.jsonToObject(adminRoleRequestVOJson,AdminRoleRequestVO.class);
        return ResultDTOUtils.success(adminService.updateAdminRoles(adminRoleRequestVO));
    }

}
