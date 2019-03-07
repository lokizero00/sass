package com.loki.sass.service.manager.controller;

import com.loki.sass.common.dto.PermissionDTO;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.PermissionRequestVO;
import com.loki.sass.service.manager.api.PermissionService;
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
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @RequestMapping(value = "v1/selectByRoleId",method = RequestMethod.POST)
    public ResultDTO<?> selectByRoleId(@RequestParam("roleId") Integer roleId)throws BizException {
        ResultDTO<List<PermissionDTO>> resultDTO = new ResultDTO<>();
        resultDTO.setSuccess(false);
        resultDTO.setModule(permissionService.selectByRoleId(roleId));
        resultDTO.setSuccess(true);
        return resultDTO;
    }

    @RequestMapping(value= "v1/addPermission", method = RequestMethod.POST)
    public ResultDTO<?> addPermission(@RequestParam("permissionRequestVOJson") String permissionRequestVOJson,
                                 @RequestParam("operatorId")Integer operatorId)throws BizException{
        ResultDTO<?> resultDTO = new ResultDTO<>();
        PermissionRequestVO permissionRequestVO = JsonUtils.jsonToObject(permissionRequestVOJson, PermissionRequestVO.class);
        resultDTO.setSuccess(permissionService.insert(permissionRequestVO));
        return resultDTO;
    }

    @RequestMapping(value="v1/editPermission",method = RequestMethod.POST)
    public ResultDTO<?> editPermission(@RequestParam("permissionRequestVOJson") String permissionRequestVOJson,
                                  @RequestParam("operatorId")Integer operatorId)throws BizException{
        ResultDTO<?> resultDTO = new ResultDTO<>();
        PermissionRequestVO permissionRequestVO = JsonUtils.jsonToObject(permissionRequestVOJson, PermissionRequestVO.class);
        resultDTO.setSuccess(permissionService.update(permissionRequestVO));
        return resultDTO;
    }

    @RequestMapping(value="v1/deletePermission",method = RequestMethod.POST)
    public ResultDTO<?> deletePermission(@RequestParam("id") Integer id,
                                    @RequestParam("operatorId") Integer operatorId)throws BizException{
        ResultDTO<?> resultDTO = new ResultDTO<>();
        resultDTO.setSuccess(permissionService.delete(id,operatorId));
        return resultDTO;
    }

    @RequestMapping(value="v1/findOne",method = RequestMethod.POST)
    public ResultDTO<?> findOne(@RequestParam("id") Integer id)throws BizException{
        ResultDTO<PermissionDTO> resultDTO = new ResultDTO<>();
        resultDTO.setSuccess(false);
        resultDTO.setModule(permissionService.findOne(id));
        resultDTO.setSuccess(true);
        return resultDTO;
    }

    @RequestMapping(value="v1/findAll",method = RequestMethod.POST)
    public ResultDTO<?> findAll()throws BizException{
        ResultDTO<List<PermissionDTO>> resultDTO = new ResultDTO<>();
        resultDTO.setSuccess(false);
        resultDTO.setModule(permissionService.findAll());
        resultDTO.setSuccess(true);
        return resultDTO;
    }
}
