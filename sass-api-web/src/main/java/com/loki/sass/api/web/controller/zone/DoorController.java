package com.loki.sass.api.web.controller.zone;

import com.loki.sass.api.web.aop.bind.Function;
import com.loki.sass.api.web.security.realm.ShiroAdmin;
import com.loki.sass.common.code.DoorResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.DoorQueryVO;
import com.loki.sass.common.vo.DoorRequestVO;
import com.loki.sass.feignclient.feignService.FeignDoorService;
import com.loki.sass.api.web.aop.bind.Operate;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * created by lokizero00 on 2019-02-21
 */
@Slf4j
@RestController
@RequestMapping("/door")
@Function(value ="门禁管理",moduleName = "小区管理",subModuleName = "")
public class DoorController {
    @Autowired
    FeignDoorService feignDoorService;

    @ApiOperation(value = "门禁查询", notes = "门禁查询，条件查询，adminId默认写0")
    @Operate(value = "门禁查询")
    @CrossOrigin
    @RequiresPermissions("doorInfo:view")//权限管理;
    @RequestMapping(value = "/oauth2/getDoorListSearch", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> getDoorListByPage(@Valid @RequestBody DoorQueryVO doorQueryVO, BindingResult bindingResult)  throws BizException {
        if (bindingResult.hasErrors()) {
            String message = String.format("查询失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        doorQueryVO.setAdminId(shiroAdmin.getId());

        return feignDoorService.getDoorListSearch(JsonUtils.objectToJson(doorQueryVO));
    }

    @ApiOperation(value="创建门禁", notes="创建门禁")
    @Operate(value = "创建门禁")
    @CrossOrigin
    @RequiresPermissions("doorInfo:add")//权限管理;
    @RequestMapping(value = "/oauth2/addDoor", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> addDoor(@Valid @RequestBody DoorRequestVO doorRequestVO, BindingResult bindingResult)  throws BizException {
        if (bindingResult.hasErrors()) {
            String message = String.format("添加失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        if (StringUtils.isEmpty(doorRequestVO.getName())
                || StringUtils.isEmpty(doorRequestVO.getCode())
                || StringUtils.isEmpty(doorRequestVO.getRemoteIp())
                || doorRequestVO.getRegionId()<=0){
            throw new BizException(DoorResultCode.DOOR_DATA_INVALID);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        doorRequestVO.setCreateBy(shiroAdmin.getId());

        return feignDoorService.addDoor(JsonUtils.objectToJson(doorRequestVO));
    }

    @ApiOperation(value="修改门禁", notes="修改门禁")
    @Operate(value = "修改门禁")
    @CrossOrigin
    @RequiresPermissions("doorInfo:edit")//权限管理;
    @RequestMapping(value = "/oauth2/editDoor", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> editDoor(@Valid @RequestBody DoorRequestVO doorRequestVO, BindingResult bindingResult)  throws BizException {
        if (bindingResult.hasErrors()) {
            String message = String.format("修改失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        if (doorRequestVO.getId()<=0
                || StringUtils.isEmpty(doorRequestVO.getName())
                || StringUtils.isEmpty(doorRequestVO.getCode())
                || StringUtils.isEmpty(doorRequestVO.getRemoteIp())
                || doorRequestVO.getRegionId()<=0){
            throw new BizException(DoorResultCode.DOOR_DATA_INVALID);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        doorRequestVO.setUpdateBy(shiroAdmin.getId());

        return feignDoorService.editDoor(JsonUtils.objectToJson(doorRequestVO));
    }

    @ApiOperation(value="删除门禁", notes="删除门禁")
    @Operate(value = "删除门禁")
    @CrossOrigin
    @RequiresPermissions("doorInfo:delete")//权限管理;
    @RequestMapping(value = "/oauth2/deleteDoor", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> deleteDoor(@RequestParam("doorId") final int doorId)  throws BizException {

        if (doorId<=0){
            throw new BizException(DoorResultCode.DOOR_ID_INVALID);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();

        return feignDoorService.deleteDoor(doorId,shiroAdmin.getId());
    }
}
