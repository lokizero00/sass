package com.loki.sass.service.manager.controller;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.RoleDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.util.ResultDTOUtils;
import com.loki.sass.common.vo.RoleQueryVO;
import com.loki.sass.common.vo.RoleRequestVO;
import com.loki.sass.service.manager.api.RoleService;
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
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "v1/selectByUserId",method = RequestMethod.POST)
    public ResultDTO<List<RoleDTO>> selectByUserId(@RequestParam("adminId") Integer adminId)throws BizException {
        return ResultDTOUtils.success(roleService.selectByUserId(adminId));
    }

    @RequestMapping(value = "v1/addRole",method = RequestMethod.POST)
    public ResultDTO<Boolean> addRole(@RequestParam("roleRequestVOJson") String roleRequestVOJson)throws BizException{
        RoleRequestVO roleRequestVO = JsonUtils.jsonToObject(roleRequestVOJson, RoleRequestVO.class);
        return ResultDTOUtils.success(roleService.insert(roleRequestVO));
    }

    @RequestMapping(value = "v1/deleteRole",method = RequestMethod.POST)
    public ResultDTO<Boolean> deleteRole( @RequestParam("id") Integer id,
                                    @RequestParam("operatorId") Integer operatorId)throws BizException{
        return ResultDTOUtils.success(roleService.deleteById(id,operatorId));
    }

    @RequestMapping(value = "v1/editRole",method = RequestMethod.POST)
    public ResultDTO<Boolean> editRole(@RequestParam("roleRequestVOJson") String roleRequestVOJson)throws BizException{
        RoleRequestVO roleRequestVO = JsonUtils.jsonToObject(roleRequestVOJson,RoleRequestVO.class);
        return ResultDTOUtils.success(roleService.update(roleRequestVO));
    }

    @RequestMapping(value = "v1/findOne",method = RequestMethod.POST)
    public ResultDTO<RoleDTO> findOne(@RequestParam("id") Integer id)throws BizException{
        return ResultDTOUtils.success(roleService.findById(id));
    }

    @RequestMapping(value = "v1/findAll",method = RequestMethod.POST)
    public ResultDTO<List<RoleDTO>> findAll()throws BizException{
        return ResultDTOUtils.success(roleService.findAll());
    }

    @RequestMapping(value = "v1/findByPage",method = RequestMethod.POST)
    public ResultDTO<PageInfo<RoleDTO>> findByPage(@RequestParam("roleQueryVOJson") String roleQueryVOJson)throws BizException{
        RoleQueryVO roleQueryVO = JsonUtils.jsonToObject(roleQueryVOJson, RoleQueryVO.class);
        return ResultDTOUtils.success(roleService.getAdminListSearch(roleQueryVO));
    }
}
