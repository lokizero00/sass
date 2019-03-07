package com.loki.sass.service.manager.controller;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.RoleDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
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
    public ResultDTO<?> selectByUserId(@RequestParam("adminId") Integer adminId)throws BizException {
        ResultDTO<List<RoleDTO>> resultDTO = new ResultDTO<>();
        resultDTO.setSuccess(false);
        resultDTO.setModule(roleService.selectByUserId(adminId));
        resultDTO.setSuccess(true);
        return resultDTO;
    }

    @RequestMapping(value = "v1/addRole",method = RequestMethod.POST)
    public ResultDTO<?> addRole(@RequestParam("roleRequestVOJson") String roleRequestVOJson)throws BizException{
        ResultDTO<Boolean> resultDTO = new ResultDTO<>();
        RoleRequestVO roleRequestVO = JsonUtils.jsonToObject(roleRequestVOJson, RoleRequestVO.class);
        resultDTO.setSuccess(roleService.insert(roleRequestVO));
        return resultDTO;
    }

    @RequestMapping(value = "v1/deleteRole",method = RequestMethod.POST)
    public ResultDTO<?> deleteRole(@RequestParam("id") Integer id,
                              @RequestParam("operatorId") Integer operatorId)throws BizException{
        ResultDTO<Boolean> resultDTO = new ResultDTO<>();
        resultDTO.setSuccess(roleService.deleteById(id,operatorId));
        return resultDTO;
    }

    @RequestMapping(value = "v1/editRole",method = RequestMethod.POST)
    public ResultDTO<?> editRole(@RequestParam("roleRequestVOJson") String roleRequestVOJson)throws BizException{
        ResultDTO<Boolean> resultDTO = new ResultDTO<>();
        RoleRequestVO roleRequestVO = JsonUtils.jsonToObject(roleRequestVOJson,RoleRequestVO.class);
        resultDTO.setSuccess(roleService.update(roleRequestVO));
        return resultDTO;
    }

    @RequestMapping(value = "v1/findOne",method = RequestMethod.POST)
    public ResultDTO<?> findOne(@RequestParam("id") Integer id)throws BizException{
        ResultDTO<RoleDTO> resultDTO = new ResultDTO<>();
        resultDTO.setSuccess(false);
        resultDTO.setModule(roleService.findById(id));
        resultDTO.setSuccess(true);
        return resultDTO;
    }

    @RequestMapping(value = "v1/findAll",method = RequestMethod.POST)
    public ResultDTO<?> findAll()throws BizException{
        ResultDTO<List<RoleDTO>> resultDTO = new ResultDTO<>();
        resultDTO.setSuccess(false);
        resultDTO.setModule(roleService.findAll());
        resultDTO.setSuccess(true);
        return resultDTO;
    }

    @RequestMapping(value = "v1/findByPage",method = RequestMethod.POST)
    public ResultDTO<?> findByPage(@RequestParam("roleQueryVOJson") String roleQueryVOJson)throws BizException{
        ResultDTO<PageInfo<RoleDTO>> resultDTO = new ResultDTO<>();
        resultDTO.setSuccess(false);
        RoleQueryVO adminQueryVO = JsonUtils.jsonToObject(roleQueryVOJson, RoleQueryVO.class);
        resultDTO.setModule(roleService.getAdminListSearch(adminQueryVO));
        resultDTO.setSuccess(true);
        return resultDTO;
    }
}
