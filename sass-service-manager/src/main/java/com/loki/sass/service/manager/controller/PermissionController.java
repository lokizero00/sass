package com.loki.sass.service.manager.controller;

import com.loki.sass.common.dto.PermissionDTO;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.util.ResultDTOUtils;
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
    private PermissionService permissionService;

    @RequestMapping(value = "v1/selectByRoleId",method = RequestMethod.POST)
    public ResultDTO<List<PermissionDTO>> selectByRoleId(@RequestParam("roleId") Integer roleId)throws BizException {
        return ResultDTOUtils.success(permissionService.selectByRoleId(roleId));
    }

    @RequestMapping(value= "v1/addPermission", method = RequestMethod.POST)
    public ResultDTO<Boolean> addPermission(@RequestParam("permissionRequestVOJson") String permissionRequestVOJson,
                                            @RequestParam("operatorId")Integer operatorId)throws BizException{
        PermissionRequestVO permissionRequestVO = JsonUtils.jsonToObject(permissionRequestVOJson, PermissionRequestVO.class);
        return ResultDTOUtils.success(permissionService.insert(permissionRequestVO));
    }

    @RequestMapping(value="v1/editPermission",method = RequestMethod.POST)
    public ResultDTO<Boolean> editPermission(@RequestParam("permissionRequestVOJson") String permissionRequestVOJson,
                                  @RequestParam("operatorId")Integer operatorId)throws BizException{
        PermissionRequestVO permissionRequestVO = JsonUtils.jsonToObject(permissionRequestVOJson, PermissionRequestVO.class);
        return ResultDTOUtils.success(permissionService.update(permissionRequestVO));
    }

    @RequestMapping(value="v1/deletePermission",method = RequestMethod.POST)
    public ResultDTO<Boolean> deletePermission(@RequestParam("id") Integer id,
                                    @RequestParam("operatorId") Integer operatorId)throws BizException{
        return ResultDTOUtils.success(permissionService.delete(id,operatorId));
    }

    @RequestMapping(value="v1/findOne",method = RequestMethod.POST)
    public ResultDTO<PermissionDTO> findOne(@RequestParam("id") Integer id)throws BizException{
        return ResultDTOUtils.success(permissionService.findOne(id));
    }

    @RequestMapping(value="v1/findAll",method = RequestMethod.POST)
    public ResultDTO<List<PermissionDTO>> findAll()throws BizException {
        return ResultDTOUtils.success(permissionService.findAll());
    }
}
